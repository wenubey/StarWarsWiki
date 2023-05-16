package com.wenubey.starwarswiki.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.presentation.components.CharacterListCard

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

    Box(modifier = Modifier.fillMaxSize()) {
        if (characters.loadState.refresh is LoadState.Loading) {
            CustomProgressBar()
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = characters.itemCount,
                    key = characters.itemKey(),
                    contentType = characters.itemContentType()
                ) { index ->
                    val item = characters[index]
                    if (item != null) {
                        CharacterListCard(navigateToDetailScreen = { navigateToDetailScreen(item) }, character = item)
                    }
                }
                item {
                    if (characters.loadState.append is LoadState.Loading) {
                        CustomProgressBar(modifier = Modifier.size(50.dp))
                    }
                }
            }
        }
    }
}