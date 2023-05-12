package com.wenubey.starwarswiki.domain.models

import com.google.gson.annotations.SerializedName

data class FilmModel(
    @SerializedName("title") val title: String?,
    @SerializedName("opening_crawl") val description: String?,
    @SerializedName("release_date") val releaseDate: String?
)