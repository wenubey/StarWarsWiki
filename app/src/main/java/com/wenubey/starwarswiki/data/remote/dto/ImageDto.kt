package com.wenubey.starwarswiki.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.starwarswiki.data.local.entities.ImageEntity

data class ImageDto(
    @SerializedName("image") val imageUrl: String,
) {
    fun mapToEntity(): ImageEntity {
        return ImageEntity(
            imageUrl = imageUrl
        )
    }
}
