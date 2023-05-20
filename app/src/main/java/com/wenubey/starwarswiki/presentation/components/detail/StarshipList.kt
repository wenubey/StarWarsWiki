package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.core.components.UndefinedBox
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.StarshipModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarshipList(
    character: CharacterModel,
    onClickStarship: (starship: StarshipModel) -> Unit
) {
    VehicleStarshipHeader(
        painterResource = R.drawable.starship,
        contentDesc = Constants.STARSHIPS_DESC,
        text = Constants.STARSHIPS
    )
    Spacer(modifier = Modifier.height(4.dp))
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        if (character.starships.isNullOrEmpty()) {
            item {
                UndefinedBox(
                    modifier = Modifier
                        .width((ScreenSize().width() / 2).dp)
                )
            }
        } else {
            items(character.starships) { starship ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(4.dp)
                        .width((ScreenSize().width() / 2).dp),
                    onClick = { onClickStarship(starship) }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(text = starship.name ?: Constants.UNDEFINED)
                        Text(text = starship.model ?: Constants.UNDEFINED)
                    }
                }
            }
        }
    }
}