package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmModel(
    @SerializedName("title") val title: String?,
    @SerializedName("opening_crawl") val description: String?,
    @SerializedName("release_date") val releaseDate: String?
) : Parcelable