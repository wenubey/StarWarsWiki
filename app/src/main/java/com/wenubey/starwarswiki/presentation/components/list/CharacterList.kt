package com.wenubey.starwarswiki.presentation.components.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.wenubey.starwarswiki.core.Constants.SEARCH
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.domain.models.CharacterModel


@Composable
fun CharacterList(
    characters: LazyPagingItems<CharacterModel>,
    navigateToDetailScreen: (character: CharacterModel) -> Unit,
    paddingValues: PaddingValues,
    searchQuery: State<String>,
    setSearchQuery: (String) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TextField(
                value = searchQuery.value,
                onValueChange = setSearchQuery,
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                label = { Text(text = SEARCH) },
            )
        }
        items(
            count = characters.itemCount,
            key = characters.itemKey(),
            contentType = characters.itemContentType()
        ) { index ->
            val item = characters[index]
            if (item != null) {
                CharacterListCard(
                    navigateToDetailScreen = { navigateToDetailScreen(item) },
                    character = item
                )
            }
        }
        item {
            if (characters.loadState.append is LoadState.Loading) {
                CustomProgressBar(modifier = Modifier.size(50.dp))
            }
        }
    }

}

