package com.wenubey.starwarswiki.core

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.PlanetModel
import com.wenubey.starwarswiki.domain.models.SpecieModel
import com.wenubey.starwarswiki.presentation.ui.theme.Auburn
import com.wenubey.starwarswiki.presentation.ui.theme.Black
import com.wenubey.starwarswiki.presentation.ui.theme.Blond
import com.wenubey.starwarswiki.presentation.ui.theme.Blue
import com.wenubey.starwarswiki.presentation.ui.theme.Brown
import com.wenubey.starwarswiki.presentation.ui.theme.Dark
import com.wenubey.starwarswiki.presentation.ui.theme.Fair
import com.wenubey.starwarswiki.presentation.ui.theme.Gold
import com.wenubey.starwarswiki.presentation.ui.theme.Gray
import com.wenubey.starwarswiki.presentation.ui.theme.Green
import com.wenubey.starwarswiki.presentation.ui.theme.GreenTan
import com.wenubey.starwarswiki.presentation.ui.theme.Hazel
import com.wenubey.starwarswiki.presentation.ui.theme.Light
import com.wenubey.starwarswiki.presentation.ui.theme.Metal
import com.wenubey.starwarswiki.presentation.ui.theme.MottledBrown
import com.wenubey.starwarswiki.presentation.ui.theme.MottledGreen
import com.wenubey.starwarswiki.presentation.ui.theme.Orange
import com.wenubey.starwarswiki.presentation.ui.theme.Pale
import com.wenubey.starwarswiki.presentation.ui.theme.Pink
import com.wenubey.starwarswiki.presentation.ui.theme.Red
import com.wenubey.starwarswiki.presentation.ui.theme.Tan
import com.wenubey.starwarswiki.presentation.ui.theme.White
import com.wenubey.starwarswiki.presentation.ui.theme.Yellow

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

fun String?.toColorList(): List<Color>? {
    val colorNames = this?.split(",")?.map { it.trim().lowercase() }

    return colorNames?.map { colorName ->
            when(colorName) {
                "red" -> Red
                "yellow" -> Yellow
                "blue" -> Blue
                "brown" -> Brown
                "gray" -> Gray
                "orange" -> Orange
                "hazel" -> Hazel
                "black" -> Black
                "pink" -> Pink
                "gold" -> Gold
                "green" -> Green
                "white" -> White
                "dark" -> Dark
                "blond" -> Blond
                "grey" -> Gray
                "auburn" -> Auburn
                "fair" -> Fair
                "light" -> Light
                "green-tan" -> GreenTan
                "pale" -> Pale
                "metal" -> Metal
                "brown mottle" -> MottledBrown
                "mottled green" -> MottledGreen
                "tan" -> Tan
                else -> Color.Transparent
            }
        }

}





inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}


