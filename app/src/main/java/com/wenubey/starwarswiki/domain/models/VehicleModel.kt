package com.wenubey.starwarswiki.domain.models

import com.google.gson.annotations.SerializedName

data class VehicleModel(
    @SerializedName("name") val name: String?,
    @SerializedName("model") val model: String?,
    @SerializedName("manufacturer") val manufacturer: String?,
    @SerializedName("cost_in_credits") val costInCredits: String?,
    @SerializedName("length") val length: String?,
    @SerializedName("crew") val crew: String?,
    @SerializedName("passengers") val passengers: String?,
    @SerializedName("consumables") val consumables: String?,
    @SerializedName("vehicle_class") val vehicleClass: String?,
)
