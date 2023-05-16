package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpecieModel(
    @SerializedName("name") val name: String? =  UNDEFINED,
    @SerializedName("classification") val classification: String?,
    @SerializedName("average_height") val averageHeight: String?,
    @SerializedName("average_lifespan") val averageLifespan: String?,
    @SerializedName("language") val language: String?,
): Parcelable
