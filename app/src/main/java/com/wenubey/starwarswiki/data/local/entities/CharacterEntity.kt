package com.wenubey.starwarswiki.data.local.entities

import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.CharacterModel

data class CharacterEntity(
    val name: String?,
    val height: String?,
    val mass: String?,
    val birthYear: String?,
    val gender: String?,
    val homeWorld: String?,
    val films: List<String>?,
    val vehicles: List<String>?,
    val species: List<String>?,
    val starships: List<String>?,
) {
    fun mapToDomainModel(): CharacterModel {
        return CharacterModel(
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

