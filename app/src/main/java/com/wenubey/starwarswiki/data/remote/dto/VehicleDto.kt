package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.data.local.entities.VehicleEntity

data class VehicleDto(
    @SerializedName("name") val name: String?,
    @SerializedName("model") val model: String?,
    @SerializedName("manufacturer") val manufacturer: String?,
    @SerializedName("cost_in_credits") val costInCredits: String?,
    @SerializedName("length") val length: String?,
    @SerializedName("crew") val crew: String?,
    @SerializedName("passengers") val passengers: String?,
    @SerializedName("consumables") val consumables: String?,
    @SerializedName("vehicle_class") val vehicleClass: String?,
) {
    fun mapToEntity(): VehicleEntity {
        return VehicleEntity(
            vehicleName = name ?: UNDEFINED,
            vehicleModel = model ?: UNDEFINED,
            vehicleManufacturer = manufacturer ?: UNDEFINED,
            vehicleCostInCredits = costInCredits ?: UNDEFINED,
            vehicleLength = length ?: UNDEFINED,
            vehicleCrew = crew ?: UNDEFINED,
            passengers = passengers ?: UNDEFINED,
            consumables = consumables ?: UNDEFINED,
            vehicleClass = vehicleClass ?: UNDEFINED,
        )
    }
}
