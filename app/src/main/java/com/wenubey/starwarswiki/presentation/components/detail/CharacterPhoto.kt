package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.domain.models.CharacterModel

@Composable
fun CharacterPhoto(
    character: CharacterModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(character.photoUrl?.imageUrl)
            .crossfade(true)
            .size(600)
            .build()
    )
    Image(
        painter = if (painter.state is AsyncImagePainter.State.Success) painter else painterResource(
            id = R.drawable.placeholder
        ),
        contentDescription = Constants.CHARACTER_PHOTO_DESC,
        modifier = modifier
            .width((ScreenSize().width() / 2).dp)
            .clip(RoundedCornerShape(16.dp))
            .padding(start = 4.dp)

    )
}