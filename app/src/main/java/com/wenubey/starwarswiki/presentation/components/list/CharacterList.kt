package com.wenubey.starwarswiki.presentation.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.core.components.ErrorScreen
import com.wenubey.starwarswiki.domain.models.CharacterModel


@Composable
fun CharacterList(
    characters: LazyPagingItems<CharacterModel>,
    navigateToDetailScreen: (character: CharacterModel) -> Unit,
    lazyGridState: LazyGridState,
    isPortrait: Boolean,
    gridChanged: Boolean,
) {
    if (isPortrait) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyGridState,
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
            columns = GridCells.Fixed(if (gridChanged) 3 else 2)
        ) {
            if (characters.itemCount > 0) {
                items(
                    count = characters.itemCount,
                    key = characters.itemKey(),
                    contentType = characters.itemContentType()
                ) { index ->
                    val item = characters[index]
                    if (item != null) {
                        CharacterListCard(
                            navigateToDetailScreen = { navigateToDetailScreen(item) },
                            character = item,
                            isPortrait = true,
                        )
                    }
                }
            } else {
                item {
                    ErrorScreen()
                }
            }
            item {
                if (characters.loadState.append is LoadState.Loading) {
                    CustomProgressBar(
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }
    } else {
        LazyHorizontalGrid(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyGridState,
            verticalArrangement = Arrangement.Center,
            rows = GridCells.Fixed(1)
        ) {
            if (characters.itemCount > 0) {
                items(
                    count = characters.itemCount,
                    key = characters.itemKey(),
                    contentType = characters.itemContentType()
                ) { index ->
                    val character = characters[index]
                    if (character != null) {
                        CharacterListCard(
                            navigateToDetailScreen = { navigateToDetailScreen(character) },
                            character = character,
                            isPortrait = false,
                        )
                    }
                }
            } else {
                item {
                    ErrorScreen()
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    if (characters.loadState.append is LoadState.Loading) {
                        CustomProgressBar(modifier = Modifier.size(50.dp))
                    }
                }
            }
        }
    }

}

