package com.wenubey.starwarswiki.domain.models

import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleVehicleStarshipModel(
    override val name: String?,
    override val model: String?,
    override val manufacturer: String?,
    override val costInCredits: String?,
    override val length: String?,
    override val crew: String?,
    override val passengers: String?,
    override val consumables: String?,
    override val modelClass: String?,
) : VehicleStarshipModel()
