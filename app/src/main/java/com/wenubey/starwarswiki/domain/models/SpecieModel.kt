package com.wenubey.starwarswiki.domain.models

import com.google.gson.annotations.SerializedName

data class SpecieModel(
    @SerializedName("name") val name: String?,
    @SerializedName("classification") val classification: String?,
    @SerializedName("average_height") val averageHeight: String?,
    @SerializedName("average_lifespan") val averageLifespan: String?,
    @SerializedName("language") val language: String?,
)
