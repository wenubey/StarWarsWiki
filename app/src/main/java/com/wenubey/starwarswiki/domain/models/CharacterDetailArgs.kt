package com.wenubey.starwarswiki.domain.models

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson


class CharacterModelNavType : NavType<CharacterModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): CharacterModel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): CharacterModel {
        return Gson().fromJson(value, CharacterModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CharacterModel) {
        bundle.putParcelable(key, value)
    }
}
