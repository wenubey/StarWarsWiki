package com.wenubey.starwarswiki.presentation.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.wenubey.starwarswiki.domain.models.FilmModel

class FilmModelNavType : NavType<FilmModel>(isNullableAllowed = false) {

    @Suppress("DEPRECATION")
    override fun get(bundle: Bundle, key: String): FilmModel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): FilmModel {
        return Gson().fromJson(value, FilmModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: FilmModel) {
        bundle.putParcelable(key, value)
    }
}
