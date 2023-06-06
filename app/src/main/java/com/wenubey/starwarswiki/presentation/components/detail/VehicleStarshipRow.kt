package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.domain.models.CharacterModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleStarshipRow(
    character: CharacterModel,
    scope: CoroutineScope,
    bottomSheetContent: MutableState<BottomSheetContent>,
    sheetState: BottomSheetScaffoldState,
    modifier: Modifier = Modifier
) {
     fun setBottomSheetContent(content: BottomSheetContent) {
        scope.launch {
            bottomSheetContent.value = content
            if (sheetState.bottomSheetState.hasPartiallyExpandedState) {
                sheetState.bottomSheetState.expand()
            } else {
                sheetState.bottomSheetState.partialExpand()
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            VehicleList(
                character = character,
                onClickVehicle = { vehicle ->
                    setBottomSheetContent(BottomSheetContent.VehicleContent(vehicle))
                },
            )
        }
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            StarshipList(
                character = character,
                onClickStarship = { starship ->
                    setBottomSheetContent(BottomSheetContent.StarshipContent(starship))
                },
            )
        }
    }


}

