package com.wenubey.starwarswiki.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.presentation.components.list.CharacterListCard

@Composable
fun CharacterList(
    characters: LazyPagingItems<CharacterModel>,
    navigateToDetailScreen: (character: CharacterModel) -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
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