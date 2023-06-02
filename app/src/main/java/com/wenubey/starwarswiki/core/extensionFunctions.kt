package com.wenubey.starwarswiki.core

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.PlanetModel
import com.wenubey.starwarswiki.domain.models.SpecieModel
import com.wenubey.starwarswiki.presentation.ui.theme.eyeBlack
import com.wenubey.starwarswiki.presentation.ui.theme.eyeBlue
import com.wenubey.starwarswiki.presentation.ui.theme.eyeBrown
import com.wenubey.starwarswiki.presentation.ui.theme.eyeDark
import com.wenubey.starwarswiki.presentation.ui.theme.eyeGold
import com.wenubey.starwarswiki.presentation.ui.theme.eyeGray
import com.wenubey.starwarswiki.presentation.ui.theme.eyeGreen
import com.wenubey.starwarswiki.presentation.ui.theme.eyeHazel
import com.wenubey.starwarswiki.presentation.ui.theme.eyeOrange
import com.wenubey.starwarswiki.presentation.ui.theme.eyePink
import com.wenubey.starwarswiki.presentation.ui.theme.eyeRed
import com.wenubey.starwarswiki.presentation.ui.theme.eyeWhite
import com.wenubey.starwarswiki.presentation.ui.theme.eyeYellow

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

fun String?.mapToColor(): List<Color> {
    val eyeColorMap = mapOf(
        "blue" to listOf(eyeBlue),
        "yellow" to listOf(eyeYellow),
        "red" to listOf(eyeRed),
        "brown" to listOf(eyeBrown),
        "blue-gray" to listOf(eyeBlue, eyeGray),
        "orange" to listOf(eyeOrange),
        "hazel" to listOf(eyeHazel),
        "black" to listOf(eyeBlack),
        "pink" to listOf(eyePink),
        "unknown" to listOf(eyeBlack),
        "red, blue" to listOf(eyeRed, eyeBlue),
        "gold" to listOf(eyeGold),
        "green, yellow" to listOf(eyeGreen, eyeYellow),
        "white" to listOf(eyeWhite),
        "dark" to listOf(eyeDark)
    )

    return eyeColorMap[this] ?: listOf(eyeBlack)
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun <T> List<T>.second(): T {
    if (isEmpty())
        throw NoSuchElementException("List is empty.")
    return this[1]
}

