package com.wenubey.starwarswiki.data.local.entities

import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.FilmModel


data class FilmEntity(
    val title: String?,
    val description: String?,
    val releaseDate: String?
) {
    fun mapToDomainModel(): FilmModel {
        return FilmModel(
            title = title ?: UNDEFINED,
            description = description ?: UNDEFINED,
            releaseDate = releaseDate ?: UNDEFINED,
        )
    }
}