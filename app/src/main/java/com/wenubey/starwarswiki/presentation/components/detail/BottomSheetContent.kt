package com.wenubey.starwarswiki.presentation.components.detail

import com.wenubey.starwarswiki.domain.models.StarshipVehicleStarshipModel
import com.wenubey.starwarswiki.domain.models.VehicleVehicleStarshipModel

sealed class BottomSheetContent(
) {
    data class VehicleContent(val vehicle: VehicleVehicleStarshipModel) : BottomSheetContent()

    data class StarshipContent(val starship: StarshipVehicleStarshipModel) : BottomSheetContent()

    object EmptyContent: BottomSheetContent()
}
