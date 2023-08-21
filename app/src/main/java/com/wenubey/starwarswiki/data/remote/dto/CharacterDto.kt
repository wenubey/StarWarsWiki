package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.getIdFromUrl
import com.wenubey.starwarswiki.core.toColorList
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.local.entities.ImageEntity

data class CharacterDto(
    @SerializedName("name") val name: String? = null,
    @SerializedName("height") val height: String? = null,
    @SerializedName("mass") val mass: String? = null,
    @SerializedName("hair_color") val hairColor: String? = null,
    @SerializedName("skin_color") val skinColor: String? = null,
    @SerializedName("eye_color") val eyeColor: String? = null,
    @SerializedName("birth_year") val birthYear: String? = null,
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("homeworld") val homeWorld: String? = null,
    @SerializedName("films") val films: List<String>? = null,
    @SerializedName("species") val species: List<String>? = null,
    @SerializedName("vehicles") val vehicles: List<String>? = null,
    @SerializedName("starships") val starships: List<String>? = null,
    @SerializedName("url") val url: String? = null,
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
