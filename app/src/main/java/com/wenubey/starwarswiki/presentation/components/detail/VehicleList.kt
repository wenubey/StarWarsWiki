package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.core.components.UndefinedBox
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.VehicleModel

@Composable
fun VehicleList(
    character: CharacterModel,
    onClickVehicle: (vehicle: VehicleModel) -> Unit
) {
    VehicleStarshipHeader(
        painterResource = R.drawable.vehicle,
        contentDesc = Constants.VEHICLES_DESC,
        text = Constants.VEHICLES
    )
    Spacer(modifier = Modifier.height(4.dp))
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        if (character.vehicles.isNullOrEmpty()) {
            item {
                UndefinedBox(
                    modifier = Modifier
                        .width((ScreenSize().width() / 2).dp)
                )
            }
        } else {
            items(character.vehicles) { vehicle ->
                VehicleItem(
                    vehicle = vehicle,
                    onClick = {
                        onClickVehicle(vehicle)
                    },
                )
            }
        }
    }
}

