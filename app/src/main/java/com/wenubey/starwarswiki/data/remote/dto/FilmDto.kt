package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.getIdFromUrl
import com.wenubey.starwarswiki.data.local.entities.FilmEntity

data class FilmDto(
    @SerializedName("url") val url: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("opening_crawl") val description: String?,
    @SerializedName("release_date") val releaseDate: String?
) {
    fun mapToEntity(): FilmEntity {
        return FilmEntity(
            title = title ?: UNDEFINED,
            description = description ?: UNDEFINED,
            releaseDate = releaseDate ?: UNDEFINED,
            filmId = url.getIdFromUrl(),
        )
    }
}