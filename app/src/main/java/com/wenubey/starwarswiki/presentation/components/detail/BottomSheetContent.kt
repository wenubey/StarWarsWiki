package com.wenubey.starwarswiki.presentation.components.detail

import com.wenubey.starwarswiki.domain.models.StarshipModel
import com.wenubey.starwarswiki.domain.models.VehicleModel

sealed class BottomSheetContent(
) {
    data class VehicleContent(val vehicle: VehicleModel) : BottomSheetContent()

    data class StarshipContent(val starship: StarshipModel) : BottomSheetContent()

    object EmptyContent: BottomSheetContent()
}
