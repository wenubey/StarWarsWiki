package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.domain.models.VehicleStarshipModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    scope: CoroutineScope,
    sheetState: BottomSheetScaffoldState,
    bottomSheetContent: BottomSheetContent,
    isPortrait: Boolean
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .height((ScreenSize().height() / 3).dp)
            .clickable(interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    scope.launch { sheetState.bottomSheetState.partialExpand() }
                }),
        contentAlignment = Alignment.Center,

        ) {
        Image(
            painter = painterResource(
                id = when (bottomSheetContent) {
                    is BottomSheetContent.VehicleContent -> {
                        R.drawable.vehicle
                    }
                    is BottomSheetContent.StarshipContent -> {
                        R.drawable.starship
                    }
                    is BottomSheetContent.EmptyContent -> {
                        R.drawable.placeholder
                    }
                }
            ), contentDescription = "", modifier = Modifier
                .fillMaxSize()
                .alpha(0.15f)
        )
        when(bottomSheetContent) {
            is BottomSheetContent.VehicleContent ->{
                VehicleContentTexts(vehicleStarshipModel = bottomSheetContent.vehicle, isPortrait = isPortrait)
            }
            is BottomSheetContent.StarshipContent -> {
                VehicleContentTexts(
                    vehicleStarshipModel = bottomSheetContent.starship,
                    isPortrait = isPortrait
                )
            }
            is BottomSheetContent.EmptyContent -> {}
        }

    }
}



@Composable
fun VehicleContentTexts(vehicleStarshipModel: VehicleStarshipModel, isPortrait: Boolean) {
    if (isPortrait) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = stringResource(id = R.string.vehicle_starship_name, vehicleStarshipModel.name ?: UNDEFINED))
            Text(text = stringResource(id = R.string.vehicle_starship_model, vehicleStarshipModel.model ?: UNDEFINED))
            Text(text = stringResource(id = R.string.vehicle_starship_cost, vehicleStarshipModel.costInCredits ?: UNDEFINED))
            Text(text = stringResource(id = R.string.vehicle_starship_length, vehicleStarshipModel.length ?: UNDEFINED))
            Text(text = stringResource(id = R.string.vehicle_starship_crew, vehicleStarshipModel.crew ?: UNDEFINED))
            Text(text = stringResource(id = R.string.vehicle_starship_passengers, vehicleStarshipModel.passengers ?: UNDEFINED))
            Text(text = stringResource(id = R.string.vehicle_starship_class, vehicleStarshipModel.modelClass ?: UNDEFINED))
            Text(text = stringResource(id = R.string.vehicle_starship_consumables, vehicleStarshipModel.consumables ?: UNDEFINED))

        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = stringResource(id = R.string.vehicle_starship_name, vehicleStarshipModel.name ?: UNDEFINED))
                Text(text = stringResource(id = R.string.vehicle_starship_model, vehicleStarshipModel.model ?: UNDEFINED))
                Text(text = stringResource(id = R.string.vehicle_starship_cost, vehicleStarshipModel.costInCredits ?: UNDEFINED))
                Text(text = stringResource(id = R.string.vehicle_starship_length, vehicleStarshipModel.length ?: UNDEFINED))
            }
            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = stringResource(id = R.string.vehicle_starship_crew, vehicleStarshipModel.crew ?: UNDEFINED))
                Text(text = stringResource(id = R.string.vehicle_starship_passengers, vehicleStarshipModel.passengers ?: UNDEFINED))
                Text(text = stringResource(id = R.string.vehicle_starship_class, vehicleStarshipModel.modelClass ?: UNDEFINED))
                Text(text = stringResource(id = R.string.vehicle_starship_consumables, vehicleStarshipModel.consumables ?: UNDEFINED))
            }
        }
    }
}




