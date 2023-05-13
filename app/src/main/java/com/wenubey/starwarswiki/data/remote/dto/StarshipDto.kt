package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.data.local.entities.StarshipEntity
import com.wenubey.starwarswiki.domain.models.StarshipModel

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
