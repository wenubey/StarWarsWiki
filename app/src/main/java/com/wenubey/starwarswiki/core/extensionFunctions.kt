package com.wenubey.starwarswiki.core

import com.wenubey.starwarswiki.data.local.entities.PlanetEntity
import com.wenubey.starwarswiki.domain.models.PlanetModel

fun String?.getIdFromUrl() : Int {
    val pattern = "/(\\d+)/$".toRegex()
    return this?.let { pattern.find(it)?.groupValues?.get(1)?.toIntOrNull() } ?: 1
}

fun emptyPlanet(): PlanetModel = PlanetModel("", "", "", "")
fun emptyPlanetEntity(): PlanetEntity = PlanetEntity("", "", "", "")

