package com.wenubey.starwarswiki.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.wenubey.starwarswiki.core.Constants.TAG
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
    showAppBar: Boolean,
) {
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val showButton by remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }
    val scope = rememberCoroutineScope()
    Log.i(TAG, "CharacterListScreen list size: ${characters.itemCount}")
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
        modifier = Modifier.fillMaxSize(),
        topBar = {
            StarWarsTopBar(
                lazyListState = lazyListState,
                showAppBar = showAppBar
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
                    CharacterSearchBar(
                        searchQuery = searchQuery.value,
                        setSearchQuery = setSearchQuery
                    )
                    if (refreshState is LoadState.Loading) {
                        CustomProgressBar(
                            modifier = Modifier.background(Color.Transparent)
                        )
                    } else {
                        CharacterList(
                            characters = characters,
                            navigateToDetailScreen = navigateToDetailScreen,
                            lazyListState = lazyListState
                        )
                    }
                }
                ScrollToTheTopButton(
                    showButton = showButton,
                    lazyListState = lazyListState,
                    scope = scope
                )
            }
        }
    )
}