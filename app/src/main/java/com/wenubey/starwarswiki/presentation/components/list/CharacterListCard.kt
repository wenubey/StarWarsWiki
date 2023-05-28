package com.wenubey.starwarswiki.presentation.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.getFirstOrNull
import com.wenubey.starwarswiki.domain.models.CharacterModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListCard(
    navigateToDetailScreen: () -> Unit,
    character: CharacterModel
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(character.photoUrl.imageUrl)
            .crossfade(false)
            .size(600)
            .build()
    )
    ElevatedCard(
        onClick = navigateToDetailScreen,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,

                ) {
                Text(text = character.name, style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = character.species.getFirstOrNull(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Image(
                painter = if (painter.state is AsyncImagePainter.State.Success) {
                    painter
                } else {
                    painterResource(id = R.drawable.character_list_not_found)
                },
                contentDescription = Constants.CHARACTER_PHOTO_DESC,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .size(48.dp),
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun CharacterListCardPreview() {
    CharacterListCard(character = mockData, navigateToDetailScreen = {})
}

