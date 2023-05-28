package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.core.ScreenSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    scope: CoroutineScope,
    sheetState: BottomSheetScaffoldState,
    bottomSheetContent: BottomSheetContent
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .height((ScreenSize().height() / 3).dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    scope.launch { sheetState.bottomSheetState.partialExpand() }
                }
            ),
        contentAlignment = Alignment.Center,

    ) {
        Image(
            painter = painterResource(id = when(bottomSheetContent) {
                is BottomSheetContent.VehicleContent -> {
                    R.drawable.vehicle
                }
                is BottomSheetContent.StarshipContent -> {
                    R.drawable.starship
                }
                is BottomSheetContent.EmptyContent -> {
                    R.drawable.placeholder
                }
            }),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.1f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            when (bottomSheetContent) {
                is BottomSheetContent.VehicleContent -> {
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_name,
                            bottomSheetContent.vehicle.name ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_model,
                            bottomSheetContent.vehicle.model ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_cost,
                            bottomSheetContent.vehicle.costInCredits ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_length,
                            bottomSheetContent.vehicle.length ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_crew,
                            bottomSheetContent.vehicle.crew ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_passengers,
                            bottomSheetContent.vehicle.passengers ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_consumables,
                            bottomSheetContent.vehicle.consumables ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_class,
                            bottomSheetContent.vehicle.vehicleClass ?: Constants.UNDEFINED
                        )
                    )
                }

                is BottomSheetContent.StarshipContent -> {
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_name,
                            bottomSheetContent.starship.name ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_model,
                            bottomSheetContent.starship.model ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_cost,
                            bottomSheetContent.starship.costInCredits ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_length,
                            bottomSheetContent.starship.length ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_crew,
                            bottomSheetContent.starship.crew ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_passengers,
                            bottomSheetContent.starship.passengers ?: Constants.UNDEFINED
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.vehicle_starship_class,
                            bottomSheetContent.starship.starshipClass ?: Constants.UNDEFINED
                        )
                    )
                }
                is BottomSheetContent.EmptyContent -> {}
            }
        }
    }
}