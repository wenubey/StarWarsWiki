package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.core.components.UndefinedBox
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.StarshipVehicleStarshipModel

@Composable
fun StarshipList(
    character: CharacterModel,
    onClickStarship: (starship: StarshipVehicleStarshipModel) -> Unit
) {
    VehicleStarshipHeader(
        painterResource = R.drawable.starship,
        contentDesc = Constants.STARSHIPS_DESC,
        text = Constants.STARSHIPS
    )
    Spacer(modifier = Modifier.height(4.dp))
    LazyColumn {
        if (character.starships.isNullOrEmpty()) {
            item {
                UndefinedBox(
                    modifier = Modifier
                        .width((ScreenSize().width() / 2).dp)
                )
            }
        } else {
            items(character.starships) { starship ->
                StarshipItem(starship = starship, onClick = { onClickStarship(starship) })
            }
        }
    }
}