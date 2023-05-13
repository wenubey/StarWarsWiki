package com.wenubey.starwarswiki.domain.models

import com.google.gson.annotations.SerializedName

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
)


