package com.wenubey.starwarswiki.presentation.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants.CHARACTER_PHOTO_DESC
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.core.getFirstOrNull
import com.wenubey.starwarswiki.domain.models.CharacterModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListCard(
    navigateToDetailScreen: () -> Unit,
    character: CharacterModel,
    isPortrait: Boolean,
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(character.photoUrl.imageUrl)
            .crossfade(false)
            .size(Size.ORIGINAL)
            .placeholder(R.drawable.character_list_not_found)
            .error(R.drawable.character_list_not_found)
            .build()
    )
    val cardSizeModifier = if (isPortrait) {
        Modifier
            .height((ScreenSize().height() / 3).dp)
    } else {
        Modifier.width((ScreenSize().width() / 5).dp)
    }
    ElevatedCard(
        onClick = navigateToDetailScreen,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .then(cardSizeModifier)
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        if (isPortrait) {
            Column(
                modifier = Modifier
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier

                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = character.species.getFirstOrNull(),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Image(
                    painter = painter,
                    contentDescription = CHARACTER_PHOTO_DESC,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.7f)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }

        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = CHARACTER_PHOTO_DESC,
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(4.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = character.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    device = "spec:width=393dp,height=808dp,dpi=480,isRound=false,chinSize=0dp,orientation=portrait"
)
@Composable
fun CharacterListCardPortraitPreview() {
    CharacterListCard(
        character = mockData,
        navigateToDetailScreen = {},
        isPortrait = true,
    )
}

@Preview(
    showBackground = true,
    device = "spec:width=393dp,height=808dp,dpi=480,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun CharacterListCardLandscapePreview() {
    CharacterListCard(
        navigateToDetailScreen = { },
        character = mockData,
        isPortrait = false,
    )
}

