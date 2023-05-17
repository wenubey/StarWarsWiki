package com.wenubey.starwarswiki.data.local.entities

import com.wenubey.starwarswiki.domain.models.ImageModel

data class ImageEntity(
    val imageUrl: String
) {
    fun mapToDomain(): ImageModel {
        return ImageModel(
            imageUrl = imageUrl
        )
    }
}
