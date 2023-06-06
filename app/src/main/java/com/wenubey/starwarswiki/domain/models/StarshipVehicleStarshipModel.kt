package com.wenubey.starwarswiki.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StarshipVehicleStarshipModel(
    override val name: String?,
    override val model: String?,
    override val manufacturer: String?,
    override val costInCredits: String?,
    override val length: String?,
    override  val crew: String?,
    override val passengers: String?,
    override val modelClass: String?,
    override val consumables: String? = null
) : VehicleStarshipModel()

sealed class VehicleStarshipModel : Parcelable {
    abstract val name: String?
    abstract val model: String?
    abstract val manufacturer: String?
    abstract val costInCredits: String?
    abstract val length: String?
    abstract val crew: String?
    abstract val passengers: String?
    abstract val modelClass: String?
    abstract val consumables: String?
}
