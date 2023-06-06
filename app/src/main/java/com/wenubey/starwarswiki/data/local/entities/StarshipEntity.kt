package com.wenubey.starwarswiki.data.local.entities

import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.StarshipVehicleStarshipModel


data class StarshipEntity(
    val starshipName: String?,
    val starshipModel: String?,
    val starshipManufacturer: String?,
    val starshipCostInCredits: String?,
    val starshipLength: String?,
    val starshipCrew: String?,
    val starshipPassengers: String?,
    val starshipClass: String?,
) {
    fun mapToDomainModel(): StarshipVehicleStarshipModel {
        return StarshipVehicleStarshipModel(
            name = starshipName ?: UNDEFINED,
            model = starshipModel ?: UNDEFINED,
            manufacturer = starshipManufacturer ?: UNDEFINED,
            costInCredits = starshipCostInCredits ?: UNDEFINED,
            length = starshipLength ?: UNDEFINED,
            crew = starshipCrew ?: UNDEFINED,
            passengers = starshipPassengers ?: UNDEFINED,
            modelClass = starshipClass ?: UNDEFINED,
        )
    }
}
