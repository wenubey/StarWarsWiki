package com.wenubey.starwarswiki.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Movie
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
import coil.compose.AsyncImage
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants.CHARACTER_PHOTO_DESC
import com.wenubey.starwarswiki.core.Constants.MOVIES
import com.wenubey.starwarswiki.core.Constants.MOVIES_DESC
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.core.components.StarWarsTopBarWithBackButton
import com.wenubey.starwarswiki.core.getFirstOrNull
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.FilmModel
import com.wenubey.starwarswiki.presentation.components.detail.CharacterSpecieList

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
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(text = character.name)
                            Text(text = character.species.getFirstOrNull())
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            AsyncImage(
                                model = character.photoUrl.imageUrl,
                                contentDescription = CHARACTER_PHOTO_DESC,
                                placeholder = painterResource(id = R.drawable.placeholder),
                                modifier = Modifier.size(width = 200.dp, height = 200.dp),
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.End
                            ) {
                                if (character.species.isNullOrEmpty()) {
                                    item {
                                        Text(text = UNDEFINED)
                                    }
                                } else {
                                    items(character.species) { specie ->
                                        CharacterSpecieList(specie = specie)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Movie,
                                contentDescription = MOVIES_DESC
                            )
                            Text(text = MOVIES)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            if (character.films.isNullOrEmpty()) {
                                item {
                                    Text(text = UNDEFINED)
                                }
                            } else {
                                items(character.films) { film ->
                                    ElevatedCard(
                                        modifier = Modifier.padding(4.dp),
                                        onClick = {
                                            navigateToFilmOpeningCrawl(film)
                                        }
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(4.dp),
                                            horizontalAlignment = Alignment.Start
                                        ) {
                                            Text(text = film.title ?: UNDEFINED)
                                            Text(text = film.releaseDate ?: UNDEFINED)
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

@Preview(showBackground = false)
@Composable
fun CharacterDetailScreenPreview() {
    CharacterDetailScreen(character = mockData, {}, {})
}