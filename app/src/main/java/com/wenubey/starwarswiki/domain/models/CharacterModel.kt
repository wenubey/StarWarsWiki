package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CharacterModel(
    val id: Int,
    val name: String,
    val height: String? = null,
    val mass: String? = null,
    val eyeColor: @RawValue List<Color>? = null,
    val skinColor: @RawValue List<Color>? = null,
    val hairColor: @RawValue List<Color>? = null,
    val birthYear: String? = null,
    val gender: String? = null,
    val homeWorld: PlanetModel? = null,
    val films: List<FilmModel>? = null,
    val vehicles: List<VehicleVehicleStarshipModel>? = null,
    val species: List<SpecieModel>?= null,
    val starships: List<StarshipVehicleStarshipModel>?= null,
    val photoUrl: ImageModel? = null,
) : Parcelable


