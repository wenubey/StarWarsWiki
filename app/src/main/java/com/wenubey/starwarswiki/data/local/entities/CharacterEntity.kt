package com.wenubey.starwarswiki.data.local.entities

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wenubey.starwarswiki.core.Constants.DATABASE_TABLE_NAME
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.emptyPlanet
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.ImageModel

@Entity(tableName = DATABASE_TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String? = null,
    val height: String?= null,
    val mass: String? = null,
    val hairColor: List<Color>? = null,
    val skinColor: List<Color>? = null,
    val eyeColor: List<Color>? = null,
    val birthYear: String? = null,
    val gender: String? = null,
    val homeWorld: PlanetEntity? = null,
    val films: List<FilmEntity>? = null,
    val vehicles: List<VehicleEntity>? = null,
    val species: List<SpecieEntity>? = null,
    val starships: List<StarshipEntity>? = null,
    val photoUrl: ImageEntity? = null,
) {
    fun mapToDomainModel(): CharacterModel {
        return CharacterModel(
            name = name ?: UNDEFINED,
            height = height ?: UNDEFINED,
            mass = mass ?: UNDEFINED,
            eyeColor = eyeColor ?: emptyList(),
            hairColor = hairColor ?: emptyList(),
            skinColor = skinColor ?: emptyList(),
            birthYear = birthYear ?: UNDEFINED,
            gender = gender ?: UNDEFINED,
            id = id,
            homeWorld = homeWorld?.mapToDomainModel() ?: emptyPlanet(),
            films = films?.map { it.mapToDomainModel() } ?: emptyList(),
            vehicles = vehicles?.map { it.mapToDomainModel() } ?: emptyList(),
            species = species?.map { it.mapToDomainModel() } ?: emptyList(),
            starships = starships?.map { it.mapToDomainModel() } ?: emptyList(),
            photoUrl = photoUrl?.mapToDomain() ?: ImageModel(""),
        )
    }
}

