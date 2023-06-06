package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.core.components.UndefinedBox
import com.wenubey.starwarswiki.domain.models.CharacterModel

@Composable
fun CharacterSpecie(
    character: CharacterModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(end = 4.dp),
        horizontalAlignment = Alignment.End
    ) {
        if (character.species.isNullOrEmpty()) {
            item {
                UndefinedBox(modifier = Modifier.width((ScreenSize().width() / 2).dp))
            }
        } else {
            items(character.species) {
                CharacterSpecieList(character = character)
            }
        }
    }
}