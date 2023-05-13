package com.wenubey.starwarswiki.data.local.entities

import androidx.room.Entity
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.PlanetModel

@Entity(tableName = "planets")
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