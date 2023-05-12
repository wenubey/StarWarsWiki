package com.wenubey.starwarswiki.data.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
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
) : DomainMapper<StarshipModel> {
    override fun mapToDomainModel(): StarshipModel {
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
