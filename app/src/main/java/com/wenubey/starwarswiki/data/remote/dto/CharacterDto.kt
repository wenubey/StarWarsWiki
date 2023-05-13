package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.getIdFromUrl
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity

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
    @SerializedName("url") val url: String?,
)  {
     fun mapToEntity(
         homeWorld: PlanetDto?,
         films: List<FilmDto>?,
         vehicles: List<VehicleDto>?,
         species: List<SpecieDto>?,
         starships: List<StarshipDto>?,
     ): CharacterEntity {
        return CharacterEntity(
            name = name ?: UNDEFINED,
            height = height ?: UNDEFINED,
            mass = mass ?: UNDEFINED,
            birthYear = birthYear ?: UNDEFINED,
            gender = gender ?: UNDEFINED,
            homeWorld = homeWorld?.mapToEntity(),
            films = films?.map { it.mapToEntity() } ?: emptyList(),
            vehicles = vehicles?.map { it.mapToEntity() } ?: emptyList(),
            species = species?.map { it.mapToEntity() } ?: emptyList(),
            starships = starships?.map { it.mapToEntity() } ?: emptyList(),
            id = url.getIdFromUrl()
        )
    }
}

data class ListCharacterDto(
    @SerializedName("results") val results: List<CharacterDto>,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
)
