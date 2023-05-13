package com.wenubey.starwarswiki.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.emptyPlanet
import com.wenubey.starwarswiki.domain.models.CharacterModel

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val height: String?,
    val mass: String?,
    val birthYear: String?,
    val gender: String?,
    @Embedded
    val homeWorld: PlanetEntity?,
    @Embedded
    val films: List<FilmEntity>?,
    @Embedded
    val vehicles: List<VehicleEntity>?,
    @Embedded
    val species: List<SpecieEntity>?,
    @Embedded
    val starships: List<StarshipEntity>?,
) {
    fun mapToDomainModel(): CharacterModel {
        return CharacterModel(
            name = name ?: UNDEFINED,
            height = height ?: UNDEFINED,
            mass = mass ?: UNDEFINED,
            birthYear = birthYear ?: UNDEFINED,
            gender = gender ?: UNDEFINED,
            id = id,
            homeWorld = homeWorld?.mapToDomainModel() ?: emptyPlanet(),
            films = films?.map { it.mapToDomainModel() } ?: emptyList(),
            vehicles = vehicles?.map { it.mapToDomainModel() } ?: emptyList(),
            species = species?.map { it.mapToDomainModel() } ?: emptyList(),
            starships = starships?.map { it.mapToDomainModel() } ?: emptyList(),
        )
    }
}

