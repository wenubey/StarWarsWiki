package com.wenubey.starwarswiki.data.remote

import com.wenubey.starwarswiki.data.remote.dto.ImageDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsImageApi {

    @GET("id/{id}.json")
    suspend fun getImageFromId(@Path("id")id: Int): ImageDto

    companion object {
        const val BASE_URL = "https://akabab.github.io/starwars-api/api/"
    }
}