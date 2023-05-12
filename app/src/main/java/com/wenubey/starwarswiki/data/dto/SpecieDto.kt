package com.wenubey.starwarswiki.data.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.SpecieModel

data class SpecieDto(
    @SerializedName("name") val name: String?,
    @SerializedName("classification") val classification: String?,
    @SerializedName("average_height") val averageHeight: String?,
    @SerializedName("average_lifespan") val averageLifespan: String?,
    @SerializedName("language") val language: String?,
): DomainMapper<SpecieModel> {
    override fun mapToDomainModel(): SpecieModel {
        return SpecieModel(
            name = name ?: UNDEFINED,
            classification = classification ?: UNDEFINED,
            averageHeight = averageHeight ?: UNDEFINED,
            averageLifespan = averageLifespan ?: UNDEFINED,
            language = language ?: UNDEFINED,
        )
    }
}
