package com.wenubey.starwarswiki.data.local.entities

import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.PlanetModel


data class PlanetEntity(
    val planetName: String?= null,
    val climate: String?= null,
    val population: String?= null,
    val terrain: String?= null,
) {
    fun mapToDomainModel(): PlanetModel {
        return PlanetModel(
            name = planetName ?: UNDEFINED,
            climate = climate ?: UNDEFINED,
            population = population ?: UNDEFINED,
            terrain = terrain ?: UNDEFINED,
        )
    }
}