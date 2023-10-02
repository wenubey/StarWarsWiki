package com.wenubey.starwarswiki

import android.net.Uri
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.printToLog
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.presentation.components.list.CharacterList
import com.wenubey.starwarswiki.presentation.navigation.Screen
import com.wenubey.starwarswiki.presentation.screens.CharacterListScreen
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    private val characters = listOf(
        CharacterModel(1, "Luke Skywalker"),
        CharacterModel(2, "Anakin Skywalker"),
        CharacterModel(3, "Leia Skywalker"),
    )
    private val charactersPagingData: PagingData<CharacterModel> = PagingData.from(characters)
    private val fakeSearchQuery: MutableState<String> = mutableStateOf("XD")
    private var fakeChecked = false
    private val fakeLazyGridState = LazyGridState()
    private val gridChange = mutableStateOf(false)
    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            CharacterListScreen(
                characters = flowOf(charactersPagingData).collectAsLazyPagingItems(),
                navigateToDetailScreen = { character ->
                    val json = Uri.encode(Gson().toJson(character))
                    navController.navigate(Screen.CharacterDetailScreen.route + "/$json")
                },
                searchQuery = fakeSearchQuery,
                setSearchQuery = { fakeSearchQuery.value = it },
                checked = fakeChecked,
                onCheckedChanged = { fakeChecked = it },
            )

            CharacterList(
                characters = flowOf(charactersPagingData).collectAsLazyPagingItems(),
                navigateToDetailScreen = { character ->

                },
                lazyGridState = fakeLazyGridState,
                isPortrait = true,
                gridChanged = gridChange.value
            )

        }
    }

    @Test
    fun testCharacterListScreenUISearch() {

        val searchBar = composeTestRule.onNodeWithTag("character_search_bar")
        fakeSearchQuery.value = "Luke Skywalker"

        searchBar.printToLog("TAG")
        searchBar.assertExists()
        searchBar.assertTextContains(fakeSearchQuery.value)

    }

    @Test
    fun testCharacterListCountIsCorrect() {
        val characterItems =
            composeTestRule.onAllNodesWithTag(Constants.CHARACTER_LIST_CARD_TEST_TAG)
        characterItems.assertCountEquals(2)

    }

    @Test
    fun testGridButtonClickedCharacterListCountIsCorrect() {
        gridChange.value = true
        val characterItems = composeTestRule.onAllNodesWithTag(Constants.CHARACTER_LIST_CARD_TEST_TAG)
        characterItems.assertCountEquals(3)
    }
    
}