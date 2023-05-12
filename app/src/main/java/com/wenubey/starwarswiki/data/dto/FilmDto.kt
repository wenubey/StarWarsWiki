package com.wenubey.starwarswiki.data.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.FilmModel

data class FilmDto(
    @SerializedName("title") val title: String?,
    @SerializedName("opening_crawl") val description: String?,
    @SerializedName("release_date") val releaseDate: String?
) : DomainMapper<FilmModel> {
    override fun mapToDomainModel(): FilmModel {
        return FilmModel(
            title = title ?: UNDEFINED,
            description = description ?: UNDEFINED,
            releaseDate = releaseDate ?: UNDEFINED,
        )
    }
}