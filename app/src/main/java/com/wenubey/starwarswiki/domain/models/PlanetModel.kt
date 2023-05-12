package com.wenubey.starwarswiki.domain.models

import com.google.gson.annotations.SerializedName

data class PlanetModel(
    @SerializedName("name") val name: String,
    @SerializedName("climate") val climate: String,
    @SerializedName("population") val population: String,
    @SerializedName("terrain") val terrain: String,
)