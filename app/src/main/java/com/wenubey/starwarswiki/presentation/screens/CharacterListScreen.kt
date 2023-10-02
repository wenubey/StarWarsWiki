package com.wenubey.starwarswiki.presentation.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.outlined.Window
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.wenubey.starwarswiki.core.Constants.CUSTOM_PROGRESS_BAR_TEST_TAG
import com.wenubey.starwarswiki.core.Constants.GRID_COUNT_CHANGE_DESC
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.core.components.ScrollToTheTopButton
import com.wenubey.starwarswiki.core.components.StarWarsTopBar
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.presentation.components.list.CharacterList
import com.wenubey.starwarswiki.presentation.components.list.CharacterSearchBar


@Composable
fun CharacterListScreen(
    characters: LazyPagingItems<CharacterModel>,
    navigateToDetailScreen: (character: CharacterModel) -> Unit,
    searchQuery: State<String>,
    setSearchQuery: (String) -> Unit,
    checked: Boolean,
    onCheckedChanged: (checked: Boolean) -> Unit,
) {
    val context = LocalContext.current
    val lazyGridState = rememberLazyGridState()
    val showButton by remember { derivedStateOf { lazyGridState.firstVisibleItemIndex > 0 } }
    val scope = rememberCoroutineScope()
    val config = LocalConfiguration.current
    val isPortrait = config.orientation == Configuration.ORIENTATION_PORTRAIT
    val gridChanged = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = characters.loadState) {
        if (characters.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (characters.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG,
            ).show()
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            StarWarsTopBar(
                isScrolled = lazyGridState.canScrollBackward,
                checked = checked,
                onCheckedChanged = onCheckedChanged
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(4.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                val refreshState = characters.loadState.refresh
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CharacterSearchBar(
                            searchQuery = searchQuery.value,
                            setSearchQuery = setSearchQuery,
                            modifier = Modifier.weight(fill = true, weight = 0.9f)
                        )
                        if (isPortrait) {
                            Icon(
                                imageVector = if (gridChanged.value) Icons.Default.GridOn else Icons.Outlined.Window,
                                contentDescription = GRID_COUNT_CHANGE_DESC,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable(
                                        enabled = true,
                                        interactionSource = MutableInteractionSource(),
                                        indication = null,
                                        onClick = {
                                            gridChanged.value = !gridChanged.value
                                        }
                                    )
                                    .testTag(GRID_COUNT_CHANGE_DESC)
                            )
                        }
                    }

                    if (refreshState is LoadState.Loading) {
                        CustomProgressBar(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .testTag(CUSTOM_PROGRESS_BAR_TEST_TAG)
                        )
                    } else {
                        CharacterList(
                            characters = characters,
                            navigateToDetailScreen = navigateToDetailScreen,
                            lazyGridState = lazyGridState,
                            isPortrait = isPortrait,
                            gridChanged = gridChanged.value,
                        )
                    }
                }
                ScrollToTheTopButton(
                    showButton = showButton,
                    lazyGridState = lazyGridState,
                    scope = scope,
                    isPortrait = isPortrait
                )
            }
        }
    )
}


