package com.wenubey.starwarswiki.data.remote

import com.wenubey.starwarswiki.data.remote.dto.FilmDto
import com.wenubey.starwarswiki.data.remote.dto.ListCharacterDto
import com.wenubey.starwarswiki.data.remote.dto.PlanetDto
import com.wenubey.starwarswiki.data.remote.dto.SpecieDto
import com.wenubey.starwarswiki.data.remote.dto.StarshipDto
import com.wenubey.starwarswiki.data.remote.dto.VehicleDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people/")
    suspend fun getCharacters(@Query("page") page: Int): ListCharacterDto

    @GET("planets/{id}/")
    suspend fun getPlanet(@Path("id") id: Int): PlanetDto

    @GET("species/{id}/")
    suspend fun getSpecie(@Path("id") id: Int): SpecieDto

    @GET("films/{id}/")
    suspend fun getFilm(@Path("id") id: Int): FilmDto

    @GET("vehicles/{id}/")
    suspend fun getVehicle(@Path("id") id: Int): VehicleDto

    @GET("starships/{id}/")
    suspend fun getStarship(@Path("id") id: Int): StarshipDto


    companion object {
        const val BASE_URL = "http://swapi.py4e.com/api/"
    }
}