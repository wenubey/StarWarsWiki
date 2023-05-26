package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.ScreenSize
import com.wenubey.starwarswiki.domain.models.VehicleModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleItem(
    vehicle: VehicleModel,
    onClick: () -> Unit

) {
    ElevatedCard(
        modifier = Modifier
            .padding(4.dp)
            .width((ScreenSize().width() / 2).dp),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(text = vehicle.name ?: Constants.UNDEFINED, textAlign = TextAlign.Center,)
            Divider(thickness = 1.dp)
            Text(text = vehicle.model ?: Constants.UNDEFINED, textAlign = TextAlign.Center,)
        }
    }
}

@Preview
@Composable
fun VehicleItemPreview() {
    VehicleItem(vehicle = mockData.vehicles!!.first(), onClick = {})
}