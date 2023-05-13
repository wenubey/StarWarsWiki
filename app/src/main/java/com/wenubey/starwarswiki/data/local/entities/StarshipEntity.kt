package com.wenubey.starwarswiki.data.local.entities

import androidx.room.Entity
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.StarshipModel

@Entity(tableName = "starships")
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
    fun mapToDomainModel(): StarshipModel {
        return StarshipModel(
            name = starshipName ?: UNDEFINED,
            model = starshipModel ?: UNDEFINED,
            manufacturer = starshipManufacturer ?: UNDEFINED,
            costInCredits = starshipCostInCredits ?: UNDEFINED,
            length = starshipLength ?: UNDEFINED,
            crew = starshipCrew ?: UNDEFINED,
            passengers = starshipPassengers ?: UNDEFINED,
            starshipClass = starshipClass ?: UNDEFINED,
        )
    }
}
