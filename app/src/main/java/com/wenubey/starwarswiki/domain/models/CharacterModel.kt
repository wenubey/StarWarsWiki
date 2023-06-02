package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CharacterModel(
    val id: Int,
    val name: String,
    val height: String,
    val mass: String,
    val eyeColor: @RawValue List<Color>,
//    val skinColor: @RawValue List<Color>,
//    val hairColor: @RawValue List<Color>,
    val birthYear: String,
    val gender: String,
    val homeWorld: PlanetModel?,
    val films: List<FilmModel>?,
    val vehicles: List<VehicleModel>?,
    val species: List<SpecieModel>?,
    val starships: List<StarshipModel>?,
    val photoUrl: ImageModel,
): Parcelable


