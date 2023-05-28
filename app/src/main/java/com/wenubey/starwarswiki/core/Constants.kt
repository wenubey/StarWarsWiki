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
    const val TOP_BAR_TITLE = "Star Wars Wiki"

    const val CHARACTER_PHOTO_DESC = "Character's Photo"
    const val CHARACTER_HEIGHT_DESC = "Character's Average Height"
    const val CHARACTER_LIFESPAN_DESC = "Character's Average Lifespan"
    const val CHARACTER_CLASSIFICATION_DESC = "Character's Classification"
    const val CHARACTER_LANGUAGE_DESC = "Character's Language "
    const val MOVIES_DESC = "Starring Movies"
    const val FILM_OPENING_CRAWL_NOT_FOUND_DESC = "Film Description Not Found"
    const val GO_BACK_PREVIOUS_SCREEN_DESC = "Go back to previous screen"
    const val VEHICLES_DESC = "Character's riding vehicles"
    const val STARSHIPS_DESC = "Character's riding starships"
    const val BACKGROUND_IMAGE_DESC = "Background Image"


    const val MOVIES = "Movies"
    const val VEHICLES = "Vehicles"
    const val STARSHIPS = "Starships"

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
                openingCrawl = "Cool Opening Crawl",
                releaseDate = "1111.11.11"
            ),
            FilmModel(
                title = "Cool Film Title2",
                openingCrawl = "Cool Opening Crawl2",
                releaseDate = "2222.22.22"
            ),
            FilmModel(
                title = "Cool Film Title3",
                openingCrawl = "Cool Opening Crawl3",
                releaseDate = "3333.33.33"
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