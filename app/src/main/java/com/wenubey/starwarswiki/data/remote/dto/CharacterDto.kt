package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.domain.models.CharacterModel

data class CharacterDto(
    @SerializedName("name") val name: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("mass") val mass: String?,
    @SerializedName("birth_year") val birthYear: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("homeworld") val homeWorld: String?,
    @SerializedName("films") val films: List<String>?,
    @SerializedName("vehicles") val vehicles: List<String>?,
    @SerializedName("species") val species: List<String>?,
    @SerializedName("starships") val starships: List<String>?,
)  {
     fun mapToEntity(): CharacterEntity {
        return CharacterEntity(
            name = name ?: UNDEFINED,
            height = height ?: UNDEFINED,
            mass = mass ?: UNDEFINED,
            birthYear = birthYear ?: UNDEFINED,
            gender = gender ?: UNDEFINED,
            homeWorld = homeWorld ?: UNDEFINED,
            films = films ?: emptyList(),
            vehicles = vehicles ?: emptyList(),
            species = species ?: emptyList(),
            starships = starships ?: emptyList(),
        )
    }
}

data class ListCharacterDto(
    @SerializedName("results") val results: List<CharacterDto>,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
)
