package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlanetModel(
    @SerializedName("name") val name: String,
    @SerializedName("climate") val climate: String,
    @SerializedName("population") val population: String,
    @SerializedName("terrain") val terrain: String,
): Parcelable