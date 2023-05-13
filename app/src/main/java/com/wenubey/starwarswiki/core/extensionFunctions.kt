package com.wenubey.starwarswiki.core

import com.wenubey.starwarswiki.domain.models.PlanetModel

fun String?.getIdFromUrl() : Int {
    val pattern = Regex("\\d+")
    return this?.let { pattern.find(it)?.value?.toIntOrNull() } ?: 1
}

fun emptyPlanet(): PlanetModel = PlanetModel("", "", "", "")

