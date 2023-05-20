package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.core.components.UndefinedBox
import com.wenubey.starwarswiki.domain.models.CharacterModel

@Composable
fun CharacterImageAndSpecieRow(
    character: CharacterModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = character.photoUrl.imageUrl,
            contentDescription = Constants.CHARACTER_PHOTO_DESC,
            placeholder = painterResource(id = R.drawable.placeholder),
            modifier = Modifier.size(width = 200.dp, height = 200.dp),
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            if (character.species.isNullOrEmpty()) {
                item {
                    UndefinedBox(modifier = Modifier.width((ScreenSize().width() / 2).dp))
                }
            } else {
                items(character.species) { specie ->
                    CharacterSpecieList(specie = specie)
                }
            }
        }
    }
}