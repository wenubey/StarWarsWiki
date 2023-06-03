package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants.CHARACTER_CLASSIFICATION_DESC
import com.wenubey.starwarswiki.core.Constants.CHARACTER_HEIGHT_DESC
import com.wenubey.starwarswiki.core.Constants.CHARACTER_LANGUAGE_DESC
import com.wenubey.starwarswiki.core.Constants.CHARACTER_LIFESPAN_DESC
import com.wenubey.starwarswiki.core.Constants.EYE_COLOR
import com.wenubey.starwarswiki.core.Constants.HAIR_COLOR
import com.wenubey.starwarswiki.core.Constants.SKIN_COLOR
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.core.components.ErrorScreen
import com.wenubey.starwarswiki.domain.models.CharacterModel

@Composable
fun CharacterSpecieList(
    character: CharacterModel
) {

    if (character.species.isNullOrEmpty()) {
        ErrorScreen(size = (ScreenSize().width() / 2).dp)
    } else {
        val specie = character.species.first()
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = specie.name ?: UNDEFINED, overflow = TextOverflow.Ellipsis, maxLines = 1)
            CharacterSpecieItem(
                imageVector = Icons.Outlined.Straighten,
                contentDesc = CHARACTER_HEIGHT_DESC,
                stringResource = R.string.specie_height,
                stringArgs = specie.averageHeight ?: UNDEFINED
            )
            CharacterSpecieItem(
                imageVector = Icons.Outlined.Favorite,
                contentDesc = CHARACTER_LIFESPAN_DESC,
                stringResource = R.string.specie_lifespan,
                stringArgs = specie.averageLifespan ?: UNDEFINED
            )
            CharacterSpecieItem(
                imageVector = Icons.Outlined.Spa,
                contentDesc = CHARACTER_CLASSIFICATION_DESC,
                stringResource = R.string.specie_classification,
                stringArgs = specie.classification ?: UNDEFINED
            )
            CharacterSpecieItem(
                imageVector = Icons.Outlined.Translate,
                contentDesc = CHARACTER_LANGUAGE_DESC,
                stringResource = R.string.specie_language,
                stringArgs = specie.language ?: UNDEFINED
            )
            CharacterColorContent(colors = character.skinColor, desc = SKIN_COLOR)
            CharacterColorContent(colors = character.eyeColor, desc = EYE_COLOR)
            CharacterColorContent(colors = character.hairColor, desc = HAIR_COLOR)

        }
    }

}

@Preview(showBackground = true)
@Composable
fun CharacterSpecieListPreview() {
    CharacterSpecieList(character = mockData)
}


