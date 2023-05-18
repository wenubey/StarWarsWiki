package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.data.local.entities.FilmEntity

data class FilmDto(
    @SerializedName("title") val title: String?,
    @SerializedName("opening_crawl") val openingCrawl: String?,
    @SerializedName("release_date") val releaseDate: String?
) {
    fun mapToEntity(): FilmEntity {
        return FilmEntity(
            title = title ?: UNDEFINED,
            openingCrawl = openingCrawl ?: UNDEFINED,
            releaseDate = releaseDate ?: UNDEFINED,
        )
    }
}