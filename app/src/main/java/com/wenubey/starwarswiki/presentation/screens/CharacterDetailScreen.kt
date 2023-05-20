package com.wenubey.starwarswiki.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants.STARSHIPS
import com.wenubey.starwarswiki.core.Constants.STARSHIPS_DESC
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.Constants.VEHICLES
import com.wenubey.starwarswiki.core.Constants.VEHICLES_DESC
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.core.components.StarWarsTopBarWithBackButton
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.FilmModel
import com.wenubey.starwarswiki.presentation.components.detail.CharacterDetailHeader
import com.wenubey.starwarswiki.presentation.components.detail.CharacterFilmList
import com.wenubey.starwarswiki.presentation.components.detail.CharacterImageAndSpecieInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: CharacterModel?,
    navigateToFilmOpeningCrawl: (film: FilmModel) -> Unit,
    navigateToBackScreen: () -> Unit,
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
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                        CharacterDetailHeader(character = character)
                        CharacterImageAndSpecieInfo(character = character)
                        CharacterFilmList(
                            filmsList = character.films,
                            navigateToFilmOpeningCrawl = navigateToFilmOpeningCrawl
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(0.5f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.vehicle),
                                        contentDescription = VEHICLES_DESC,
                                        modifier = Modifier.size(24.dp),
                                    )
                                    Text(text = VEHICLES)
                                }
                                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                                    if (character.vehicles.isNullOrEmpty()) {
                                        item {
                                            Text(text = UNDEFINED)
                                        }
                                    } else {
                                        items(character.vehicles) { vehicle ->
                                            ElevatedCard(
                                                modifier = Modifier
                                                    .padding(4.dp)
                                                    .width((ScreenSize().width() / 2).dp),
                                                onClick = {/* TODO */ },
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .padding(4.dp),
                                                    horizontalAlignment = Alignment.Start,
                                                    verticalArrangement = Arrangement.spacedBy(2.dp)
                                                ) {
                                                    Text(text = vehicle.name ?: UNDEFINED)
                                                    Text(text = vehicle.model ?: UNDEFINED)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier.weight(0.5f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.starship),
                                        contentDescription = STARSHIPS_DESC,
                                        modifier = Modifier.size(24.dp),
                                    )
                                    Text(text = STARSHIPS)
                                }
                                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                                    if (character.starships.isNullOrEmpty()) {
                                        item {
                                            Text(text = UNDEFINED)
                                        }
                                    } else {
                                        items(character.starships) { starship ->

                                            ElevatedCard(
                                                modifier = Modifier
                                                    .padding(4.dp)
                                                    .width((ScreenSize().width() / 2).dp),
                                                onClick = {/* TODO */ }
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .padding(4.dp),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = Arrangement.spacedBy(2.dp)
                                                ) {
                                                    Text(text = starship.name ?: UNDEFINED)
                                                    Text(text = starship.model ?: UNDEFINED)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
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
    CharacterDetailScreen(character = mockData, {}, {})
}