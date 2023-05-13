package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.data.local.entities.StarshipEntity

data class StarshipDto(
    @SerializedName("name") val name: String?,
    @SerializedName("model") val model: String?,
    @SerializedName("manufacturer") val manufacturer: String?,
    @SerializedName("cost_in_credits") val costInCredits: String?,
    @SerializedName("length") val length: String?,
    @SerializedName("crew") val crew: String?,
    @SerializedName("passengers") val passengers: String?,
    @SerializedName("starship_class") val starshipClass: String?,
){
     fun mapToEntity(): StarshipEntity {
        return StarshipEntity(
            starshipName = name ?: UNDEFINED,
            starshipModel = model ?: UNDEFINED,
            starshipManufacturer = manufacturer ?: UNDEFINED,
            starshipCostInCredits = costInCredits ?: UNDEFINED,
            starshipLength = length ?: UNDEFINED,
            starshipCrew = crew ?: UNDEFINED,
            starshipPassengers = passengers ?: UNDEFINED,
            starshipClass = starshipClass ?: UNDEFINED,
        )
    }
}
