package com.wenubey.starwarswiki.data.local.entities

import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.StarshipModel

data class StarshipEntity(
    val name: String?,
    val model: String?,
    val manufacturer: String?,
    val costInCredits: String?,
    val length: String?,
    val crew: String?,
    val passengers: String?,
    val starshipClass: String?,
) {
    fun mapToDomainModel(): StarshipModel {
        return StarshipModel(
            name = name ?: UNDEFINED,
            model = model ?: UNDEFINED,
            manufacturer = manufacturer ?: UNDEFINED,
            costInCredits = costInCredits ?: UNDEFINED,
            length = length ?: UNDEFINED,
            crew = crew ?: UNDEFINED,
            passengers = passengers ?: UNDEFINED,
            starshipClass = starshipClass ?: UNDEFINED,
        )
    }
}
