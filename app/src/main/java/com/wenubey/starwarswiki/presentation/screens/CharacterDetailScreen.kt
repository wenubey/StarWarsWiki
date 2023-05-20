package com.wenubey.starwarswiki.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.core.components.StarWarsTopBarWithBackButton
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.FilmModel
import com.wenubey.starwarswiki.domain.models.StarshipModel
import com.wenubey.starwarswiki.domain.models.VehicleModel
import com.wenubey.starwarswiki.presentation.components.detail.CharacterDetailHeader
import com.wenubey.starwarswiki.presentation.components.detail.VehicleStarshipRow
import com.wenubey.starwarswiki.presentation.components.detail.CharacterFilmList
import com.wenubey.starwarswiki.presentation.components.detail.CharacterImageAndSpecieRow

@Composable
fun CharacterDetailScreen(
    character: CharacterModel?,
    navigateToFilmOpeningCrawl: (film: FilmModel) -> Unit,
    navigateToBackScreen: () -> Unit,
    onClickVehicle: (vehicle: VehicleModel) -> Unit,
    onClickStarship: (starship: StarshipModel) -> Unit,
) {
    Scaffold(
        topBar = {
            StarWarsTopBarWithBackButton(
                navigateToBackScreen = navigateToBackScreen
            )
        },
        content = { paddingValues ->
            if (character != null) {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                        , colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        CharacterDetailHeader(character = character)
                        CharacterImageAndSpecieRow(character = character)
                        CharacterFilmList(
                            filmsList = character.films,
                            navigateToFilmOpeningCrawl = navigateToFilmOpeningCrawl
                        )
                        VehicleStarshipRow(
                            character = character,
                            onClickVehicle = onClickVehicle,
                            onClickStarship = onClickStarship,
                        )
                    }
                }
            } else {
                CustomProgressBar()
            }

        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CharacterDetailScreenPreview() {
    CharacterDetailScreen(character = mockData, {}, {}, {}, {})
}