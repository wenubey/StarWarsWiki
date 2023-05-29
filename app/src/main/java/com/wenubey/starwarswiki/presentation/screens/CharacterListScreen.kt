package com.wenubey.starwarswiki.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.wenubey.starwarswiki.core.components.OpeningQuote
import com.wenubey.starwarswiki.core.components.StarWarsTopBar
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.presentation.components.list.CharacterList


@Composable
fun CharacterListScreen(
    characters: LazyPagingItems<CharacterModel>,
    navigateToDetailScreen: (character: CharacterModel) -> Unit,
    searchQuery: State<String>,
    setSearchQuery: (String) -> Unit
) {
    val context = LocalContext.current

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
            StarWarsTopBar()
        },
        content = { paddingValues ->
            val refreshState = characters.loadState.refresh
            if (refreshState is LoadState.Loading) {
                OpeningQuote(paddingValues = paddingValues)
            } else {
                Column(modifier = Modifier.fillMaxSize()) {

                    CharacterList(
                        characters = characters,
                        navigateToDetailScreen = navigateToDetailScreen,
                        paddingValues = paddingValues,
                        searchQuery = searchQuery,
                        setSearchQuery = setSearchQuery
                    )
                }

            }


        }
    )
}