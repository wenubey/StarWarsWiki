package com.wenubey.starwarswiki.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wenubey.starwarswiki.core.Constants

@Composable
fun StarWarsTopBarWithBackButton(
    navigateToBackScreen: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        IconButton(
            onClick = navigateToBackScreen, modifier = Modifier.align(
                Alignment.CenterStart
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = Constants.GO_BACK_PREVIOUS_SCREEN_DESC
            )
        }
        Text(
            text = Constants.TOP_BAR_TITLE,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            fontSize = 24.sp
        )
    }
}