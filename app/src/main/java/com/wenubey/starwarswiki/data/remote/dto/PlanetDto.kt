package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.data.local.entities.PlanetEntity
import com.wenubey.starwarswiki.domain.models.PlanetModel

data class PlanetDto(
    @SerializedName("name") val name: String?,
    @SerializedName("climate") val climate: String?,
    @SerializedName("population") val population: String?,
    @SerializedName("terrain") val terrain: String?,
) {
    fun mapToEntity(): PlanetEntity {
        return PlanetEntity(
            name = name ?: UNDEFINED,
            climate = climate ?: UNDEFINED,
            population = population ?: UNDEFINED,
            terrain = terrain ?: UNDEFINED,
        )
    }
}