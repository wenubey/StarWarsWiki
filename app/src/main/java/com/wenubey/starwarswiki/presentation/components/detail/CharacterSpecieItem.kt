package com.wenubey.starwarswiki.presentation.components.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties


@Composable
fun CharacterSpecieItem(
    imageVector: ImageVector,
    contentDesc: String,
    @StringRes stringResource: Int,
    stringArgs: String,
) {
    var showDialog by remember { mutableStateOf(false) }

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
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    showDialog = true

                },
            )
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {},
            title = {},
            text = {
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
                        textAlign = TextAlign.Center,
                    )
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            )

        )
    }
}