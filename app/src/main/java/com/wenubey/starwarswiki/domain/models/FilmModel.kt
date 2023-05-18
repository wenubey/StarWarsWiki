package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmModel(
    val title: String?,
    val openingCrawl: String?,
    val releaseDate: String?
) : Parcelable