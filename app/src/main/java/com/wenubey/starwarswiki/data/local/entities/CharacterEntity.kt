package com.wenubey.starwarswiki.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wenubey.starwarswiki.core.Constants.DATABASE_TABLE_NAME
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.emptyPlanet
import com.wenubey.starwarswiki.domain.models.CharacterModel

@Entity(tableName = DATABASE_TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val height: String?,
    val mass: String?,
    val birthYear: String?,
    val gender: String?,
    val homeWorld: PlanetEntity?,
    val films: List<FilmEntity>?,
    val vehicles: List<VehicleEntity>?,
    val species: List<SpecieEntity>?,
    val starships: List<StarshipEntity>?,
    val photoUrl: ImageEntity,
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
            photoUrl = photoUrl.mapToDomain()
        )
    }
}

