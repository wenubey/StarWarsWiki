package com.wenubey.starwarswiki.data.local.entities

import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.FilmModel


data class FilmEntity(
    val title: String?,
    val openingCrawl: String?,
    val releaseDate: String?
) {
    fun mapToDomainModel(): FilmModel {
        return FilmModel(
            title = title ?: UNDEFINED,
            openingCrawl = openingCrawl ?: UNDEFINED,
            releaseDate = releaseDate ?: UNDEFINED,
        )
    }
}