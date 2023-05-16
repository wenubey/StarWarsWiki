package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class StarshipModel(
    @SerializedName("name") val name: String?,
    @SerializedName("model") val model: String?,
    @SerializedName("manufacturer") val manufacturer: String?,
    @SerializedName("cost_in_credits") val costInCredits: String?,
    @SerializedName("length") val length: String?,
    @SerializedName("crew") val crew: String?,
    @SerializedName("passengers") val passengers: String?,
    @SerializedName("starship_class") val starshipClass: String?,
): Parcelable
