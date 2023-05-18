package com.wenubey.starwarswiki.core

import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.FilmModel
import com.wenubey.starwarswiki.domain.models.ImageModel
import com.wenubey.starwarswiki.domain.models.PlanetModel
import com.wenubey.starwarswiki.domain.models.SpecieModel
import com.wenubey.starwarswiki.domain.models.StarshipModel
import com.wenubey.starwarswiki.domain.models.VehicleModel

object Constants {

    const val TAG = "starWarsTAG"
    const val UNDEFINED = "Undefined"
    const val DATABASE_TABLE_NAME = "characters"

    const val CHARACTER_PHOTO_DESC = "Character's Photo"
    const val CHARACTER_HEIGHT_DESC = "Character's Average Height"
    const val CHARACTER_LIFESPAN_DESC = "Character's Average Lifespan"
    const val CHARACTER_CLASSIFICATION_DESC = "Character's Classification"
    const val CHARACTER_LANGUAGE_DESC = "Character's Language "
    const val MOVIES_DESC = "Starring Movies"


    const val MOVIES = "Movies"

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
                openingCrawl = "Cool description",
                releaseDate = "01.01.1111"
            ),
            FilmModel(
                title = "Cool Film Title2",
                openingCrawl = "Cool description2",
                releaseDate = "02.02.2222"
            ),
            FilmModel(
                title = "Cool Film Title3",
                openingCrawl = "Cool description3",
                releaseDate = "03.03.3333"
            ),
            FilmModel(
                title = "Cool Film Title4",
                openingCrawl = "Cool description4",
                releaseDate = "04.04.4444"
            ),
            FilmModel(
                title = "Cool Film Title5",
                openingCrawl = "Cool description5",
                releaseDate = "05.05.5555"
            ),
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
                averageLifespan = "indefinite",
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
        ),
        photoUrl = ImageModel("https://vignette.wikia.nocookie.net/starwars/images/2/20/LukeTLJ.jpg")
    )
}