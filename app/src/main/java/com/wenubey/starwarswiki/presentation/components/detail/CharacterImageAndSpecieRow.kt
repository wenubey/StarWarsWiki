package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants.CHARACTER_PHOTO_DESC
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.core.components.UndefinedBox
import com.wenubey.starwarswiki.domain.models.CharacterModel

@Composable
fun CharacterImageAndSpecieRow(
    character: CharacterModel
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(character.photoUrl.imageUrl)
            .crossfade(true)
            .size(600)
            .build()
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = if (painter.state is AsyncImagePainter.State.Success) painter else painterResource(
                id = R.drawable.placeholder
            ),
            contentDescription = CHARACTER_PHOTO_DESC,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .padding(start = 4.dp)

        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
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
}