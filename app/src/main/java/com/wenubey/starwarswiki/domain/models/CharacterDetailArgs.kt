package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDetailArgs(
    val character: CharacterModel
): Parcelable
