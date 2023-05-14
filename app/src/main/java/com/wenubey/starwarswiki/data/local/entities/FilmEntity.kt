package com.wenubey.starwarswiki.data.local.entities

import androidx.room.PrimaryKey
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.FilmModel


data class FilmEntity(
    @PrimaryKey val filmId: Int,
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