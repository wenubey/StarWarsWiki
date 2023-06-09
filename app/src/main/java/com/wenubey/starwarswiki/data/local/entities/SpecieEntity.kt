package com.wenubey.starwarswiki.data.local.entities

import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.SpecieModel


data class SpecieEntity(
    val specieName: String?,
    val classification: String?,
    val averageHeight: String?,
    val averageLifespan: String?,
    val language: String?,
) {
    fun mapToDomainModel(): SpecieModel {
        return SpecieModel(
            name = specieName ?: UNDEFINED,
            classification = classification ?: UNDEFINED,
            averageHeight = averageHeight ?: UNDEFINED,
            averageLifespan = averageLifespan ?: UNDEFINED,
            language = language ?: UNDEFINED,
        )
    }
}
