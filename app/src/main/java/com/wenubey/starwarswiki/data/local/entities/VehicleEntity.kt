package com.wenubey.starwarswiki.data.local.entities

import androidx.room.Entity
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.VehicleModel

@Entity(tableName = "vehicles")
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
    fun mapToDomainModel(): VehicleModel {
        return VehicleModel(
            name = vehicleName ?: UNDEFINED,
            model = vehicleModel ?: UNDEFINED,
            manufacturer = vehicleManufacturer ?: UNDEFINED,
            costInCredits = vehicleCostInCredits ?: UNDEFINED,
            length = vehicleLength ?: UNDEFINED,
            crew = vehicleCrew ?: UNDEFINED,
            passengers = passengers ?: UNDEFINED,
            consumables = consumables ?: UNDEFINED,
            vehicleClass = vehicleClass ?: UNDEFINED,
        )
    }
}
