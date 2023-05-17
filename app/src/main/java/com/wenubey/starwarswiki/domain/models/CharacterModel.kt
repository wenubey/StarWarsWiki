package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val id: Int,
    val name: String,
    val height: String,
    val mass: String,
    val birthYear: String,
    val gender: String,
    val homeWorld: PlanetModel?,
    val films: List<FilmModel>?,
    val vehicles: List<VehicleModel>?,
    val species: List<SpecieModel>?,
    val starships: List<StarshipModel>?,
    val photoUrl: ImageModel,
): Parcelable


