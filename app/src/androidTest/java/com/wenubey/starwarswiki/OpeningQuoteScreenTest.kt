package com.wenubey.starwarswiki

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.presentation.navigation.NavGraph
import com.wenubey.starwarswiki.presentation.navigation.Screen
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpeningQuoteScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    private val characters = listOf(
        CharacterModel(1, "Luke Skywalker"),
        CharacterModel(2, "Anakin Skywalker"),
        CharacterModel(3, "Leia Skywalker"),
    )
    private val charactersPagingData: PagingData<CharacterModel> = PagingData.from(characters)
    private val fakeSearchQuery: MutableState<String> = mutableStateOf("")
    private var fakeChecked = false

    private fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavGraph(
                navHostController = navController,
                characters = flowOf(charactersPagingData).collectAsLazyPagingItems(),
                searchQuery = fakeSearchQuery,
                setSearchQuery = { fakeSearchQuery.value = it },
                checked = fakeChecked,
                onCheckedChanged = { fakeChecked = it }
            )
        }
    }

    @Test
    fun default_OpeningQuoteScreen_IsDisplayed() {

        setUp()

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")
        Thread.sleep(5000L)
        composeTestRule.onNodeWithContentDescription(Screen.OpeningQuoteScreen.route).assertExists()
    }

}

