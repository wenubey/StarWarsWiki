package com.wenubey.starwarswiki.core

import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.FilmModel
import com.wenubey.starwarswiki.domain.models.PlanetModel
import com.wenubey.starwarswiki.domain.models.SpecieModel
import com.wenubey.starwarswiki.domain.models.StarshipModel
import com.wenubey.starwarswiki.domain.models.VehicleModel

object Constants {

    const val TAG = "starWarsTAG"
    const val UNDEFINED = "Undefined"
    const val DATABASE_TABLE_NAME = "characters"

    val mockData = CharacterModel(
        id = 0,
        name = "Cool Name",
        height = "Cool Height",
        mass = "100",
        birthYear = "100BBY",
        gender = "Cool Gender",
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
}