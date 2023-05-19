package com.wenubey.starwarswiki.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.core.components.StarWarsTopBar
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.presentation.components.CharacterList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    characters: LazyPagingItems<CharacterModel>,
    navigateToDetailScreen: (character: CharacterModel) -> Unit,
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
            if (characters.loadState.refresh is LoadState.Loading) {
                CustomProgressBar()
            } else {
                CharacterList(
                    characters = characters,
                    navigateToDetailScreen = navigateToDetailScreen,
                    paddingValues = paddingValues
                )
            }
        }
    )
}