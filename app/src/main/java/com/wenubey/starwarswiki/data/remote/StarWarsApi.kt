package com.wenubey.starwarswiki.data.remote

import com.wenubey.starwarswiki.data.remote.dto.FilmDto
import com.wenubey.starwarswiki.data.remote.dto.ListCharacterDto
import com.wenubey.starwarswiki.data.remote.dto.PlanetDto
import com.wenubey.starwarswiki.data.remote.dto.SpecieDto
import com.wenubey.starwarswiki.data.remote.dto.StarshipDto
import com.wenubey.starwarswiki.data.remote.dto.VehicleDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsApi {

    @GET("people/")
    suspend fun getCharacters(@Query("page") page: Int): Response<ListCharacterDto>

    @GET
    suspend fun getPlanet(@Url planetUrl: String): Response<PlanetDto>

    @GET
    suspend fun getSpecie(@Url specieUrl: String): Response<SpecieDto>

    @GET
    suspend fun getFilm(@Url filmUrl: String): Response<FilmDto>

    @GET
    suspend fun getVehicle(@Url vehicleUrl: String): Response<VehicleDto>

    @GET
    suspend fun getStarship(@Url starshipUrl: String): Response<StarshipDto>

    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
    }
}