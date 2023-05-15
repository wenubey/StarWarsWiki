package com.wenubey.starwarswiki.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
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
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.core.components.CustomProgressBar

@Composable
fun CharacterListScreen(
    characters: LazyPagingItems<CharacterModel>
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
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = characters.itemCount,
                    key = characters.itemKey(),
                    contentType = characters.itemContentType()
                ) { index ->
                    val item = characters[index]
                    if (item != null) {
                        Column {
                            Text(text = item.name)
                            Text(text = item.homeWorld?.name ?: UNDEFINED)
                        }
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