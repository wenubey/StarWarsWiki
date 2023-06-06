package com.wenubey.starwarswiki.data.local.entities

import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.VehicleVehicleStarshipModel


data class VehicleEntity(
    val vehicleName: String?,
    val vehicleModel: String?,
    val vehicleManufacturer: String?,
    val vehicleCostInCredits: String?,
    val vehicleLength: String?,
    val vehicleCrew: String?,
    val passengers: String?,
    val consumables: String?,
    val vehicleClass: String?,
) {
    fun mapToDomainModel(): VehicleVehicleStarshipModel {
        return VehicleVehicleStarshipModel(
            name = vehicleName ?: UNDEFINED,
            model = vehicleModel ?: UNDEFINED,
            manufacturer = vehicleManufacturer ?: UNDEFINED,
            costInCredits = vehicleCostInCredits ?: UNDEFINED,
            length = vehicleLength ?: UNDEFINED,
            crew = vehicleCrew ?: UNDEFINED,
            passengers = passengers ?: UNDEFINED,
            consumables = consumables ?: UNDEFINED,
            modelClass = vehicleClass ?: UNDEFINED,
        )
    }
}
