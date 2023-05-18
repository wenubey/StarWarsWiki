package com.wenubey.starwarswiki.presentation.components.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CharacterSpecieItem(
    imageVector: ImageVector,
    contentDesc: String,
    @StringRes stringResource: Int,
    stringArgs: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDesc
        )
        Text(
            text = stringResource(stringResource, stringArgs),
            textAlign = TextAlign.Center
        )

    }
}