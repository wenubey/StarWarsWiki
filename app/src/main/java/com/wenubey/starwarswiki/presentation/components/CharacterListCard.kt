package com.wenubey.starwarswiki.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.FilmModel
import com.wenubey.starwarswiki.domain.models.PlanetModel
import com.wenubey.starwarswiki.domain.models.SpecieModel
import com.wenubey.starwarswiki.domain.models.StarshipModel
import com.wenubey.starwarswiki.domain.models.VehicleModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListCard(
    navigateToDetailScreen: () -> Unit,
    character: CharacterModel
) {
    Card(
        onClick = navigateToDetailScreen
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

        }
    }
}

val mockData = CharacterModel(
    id = 0,
    name = "Cool Name",
    height = "Cool Name",
    mass = "Cool Name",
    birthYear = "Cool Name",
    gender = "Cool Name",
    homeWorld = PlanetModel(
        name = "Cool Planet",
        climate = "Cool Climate",
        population = "100000",
        terrain = "Cool Terrain"
    ),
    films = listOf(
        FilmModel(
            title = "Cool Film Title",
            description = "Cool description",
            releaseDate = "01.01.1111"
        )
    ),
    vehicles = listOf(
        VehicleModel(
            name = "Cool Vehicle Name",
            model = "Cool Vehicle Model",
            manufacturer = "Cool Vehicle Manufacturer",
            costInCredits = "50000",
            length = "1000",
            crew = "10",
            passengers = "5",
            consumables = "Cool Consumable",
            vehicleClass = "Cool Vehicle Class"
        )
    ),
    species = listOf(
        SpecieModel(
            name = "Cool Specie Name",
            classification = "Cool Specie Classification",
            averageHeight = "100",
            averageLifespan = "10",
            language = "Cool Specie Language"
        )
    ),
    starships = listOf(
        StarshipModel(
            name = "Cool Starship Name",
            model = "Cool Starship Name",
            manufacturer = "Cool Starship Manufacturer",
            costInCredits = "10000",
            length = "1000",
            crew = "100",
            passengers = "100",
            starshipClass = "Cool Starship Class",
        )
    )
)

@Preview
@Composable
fun CharacterListCardPreview() {
    CharacterListCard(character = mockData, navigateToDetailScreen = {})
}