package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageModel(
    val imageUrl: String
): Parcelable