package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.getIdFromUrl
import com.wenubey.starwarswiki.core.toColorList
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.local.entities.ImageEntity

data class CharacterDto(
    @SerializedName("name") val name: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("mass") val mass: String?,
    @SerializedName("hair_color") val hairColor: String?,
    @SerializedName("skin_color") val skinColor: String?,
    @SerializedName("eye_color") val eyeColor: String?,
    @SerializedName("birth_year") val birthYear: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("homeworld") val homeWorld: String?,
    @SerializedName("films") val films: List<String>?,
    @SerializedName("species") val species: List<String>?,
    @SerializedName("vehicles") val vehicles: List<String>?,
    @SerializedName("starships") val starships: List<String>?,
    @SerializedName("url") val url: String?,
)  {
     fun mapToEntity(
         homeWorld: PlanetDto?,
         films: List<FilmDto>?,
         vehicles: List<VehicleDto>?,
         species: List<SpecieDto>?,
         starships: List<StarshipDto>?,
         photoUrl: ImageDto?,
     ): CharacterEntity {
        return CharacterEntity(
            name = name ?: UNDEFINED,
            height = height ?: UNDEFINED,
            mass = mass ?: UNDEFINED,
            eyeColor = eyeColor.toColorList() ?: emptyList(),
            hairColor = hairColor.toColorList() ?: emptyList(),
            skinColor = skinColor.toColorList() ?: emptyList(),
            birthYear = birthYear ?: UNDEFINED,
            gender = gender ?: UNDEFINED,
            homeWorld = homeWorld?.mapToEntity(),
            films = films?.map { it.mapToEntity() } ?: emptyList(),
            vehicles = vehicles?.map { it.mapToEntity() } ?: emptyList(),
            species = species?.map { it.mapToEntity() } ?: emptyList(),
            starships = starships?.map { it.mapToEntity() } ?: emptyList(),
            id = url.getIdFromUrl(),
            photoUrl = photoUrl?.mapToEntity() ?: ImageEntity("")
        )
    }
}

data class ListCharacterDto(
    @SerializedName("results") val results: List<CharacterDto>,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
)
