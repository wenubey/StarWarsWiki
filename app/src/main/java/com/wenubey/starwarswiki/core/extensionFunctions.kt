package com.wenubey.starwarswiki.core

import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.PlanetModel
import com.wenubey.starwarswiki.domain.models.SpecieModel

fun String?.getIdFromUrl() : Int {
    val pattern = "/(\\d+)/$".toRegex()
    return this?.let { pattern.find(it)?.groupValues?.get(1)?.toIntOrNull() } ?: 1
}

fun emptyPlanet(): PlanetModel = PlanetModel("", "", "", "")

fun List<SpecieModel>?.getFirstOrNull(): String {
    return if (this.isNullOrEmpty()) {
        UNDEFINED
    } else {
        this.first().name!!
    }
}



