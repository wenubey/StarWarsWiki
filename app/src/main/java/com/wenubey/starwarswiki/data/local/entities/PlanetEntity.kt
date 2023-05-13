package com.wenubey.starwarswiki.data.local.entities

import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.PlanetModel

data class PlanetEntity(
    val name: String?,
    val climate: String?,
    val population: String?,
    val terrain: String?,
) {
    fun mapToDomainModel(): PlanetModel {
        return PlanetModel(
            name = name ?: UNDEFINED,
            climate = climate ?: UNDEFINED,
            population = population ?: UNDEFINED,
            terrain = terrain ?: UNDEFINED,
        )
    }
}