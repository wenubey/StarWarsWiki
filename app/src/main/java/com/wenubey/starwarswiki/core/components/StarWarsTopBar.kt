@file:JvmName("StarWarsTopBarKt")

package com.wenubey.starwarswiki.core.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Copyright
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.core.Constants.COPYRIGHT_DESC
import com.wenubey.starwarswiki.core.Constants.COPYRIGHT_HEADER
import com.wenubey.starwarswiki.core.Constants.TOP_BAR_HEIGHT
import com.wenubey.starwarswiki.presentation.ui.theme.OpeningQuoteColor

@Composable
fun StarWarsTopBar(
    navigateToBackScreen: (() -> Unit)? = null,
    isScrolled: Boolean = false,
    checked: Boolean = false,
    onCheckedChanged: ((checked: Boolean) -> Unit)? = null,

    ) {
    val isDialogShowed = remember {
        mutableStateOf(false)
    }

    CopyrightAlertDialog(isDialogShowed = isDialogShowed)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .animateContentSize(animationSpec = tween(durationMillis = 300))
            .height(height = if (isScrolled) 0.dp else TOP_BAR_HEIGHT.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (navigateToBackScreen != null) {
            IconButton(
                onClick = {
                    navigateToBackScreen.invoke()
                },
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = Constants.GO_BACK_PREVIOUS_SCREEN_DESC
                )
            }
        }
        Text(
            text = Constants.TOP_BAR_TITLE,
            modifier = Modifier
                .weight(0.65f)
                .padding(16.dp),
            fontSize = 24.sp
        )
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(0.35f)
        ) {
            IconButton(
                onClick = {
                    isDialogShowed.value = !isDialogShowed.value
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Copyright,
                    contentDescription = COPYRIGHT_DESC,
                    modifier = Modifier.size(30.dp),
                    tint = if (checked) MaterialTheme.colorScheme.primary  else  MaterialTheme.colorScheme.outline
                )

            }

            if (onCheckedChanged != null) {
                ThemeModeSwitch(
                    modifier = Modifier
                        .padding(4.dp),
                    checked = checked,
                    onCheckedChanged = onCheckedChanged
                )
            }
        }


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CopyrightAlertDialog(
    isDialogShowed: MutableState<Boolean>,
) {
    val uriHandler = LocalUriHandler.current
    if (isDialogShowed.value) {
        AlertDialog(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp)),
            onDismissRequest = {
                isDialogShowed.value = false
            }
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(8.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                    Text(
                        text = COPYRIGHT_HEADER,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = OpeningQuoteColor
                        ),
                    )
                    CopyrightText(uriHandler = uriHandler)
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CopyrightDialogPreview() {
    val isDialogShowed = remember {
        mutableStateOf(true)
    }
    CopyrightAlertDialog(isDialogShowed = isDialogShowed)
}

@Preview
@Composable
fun StarWarsTopBarPreview() {
    StarWarsTopBar(
        checked = false,
        onCheckedChanged = {},
        navigateToBackScreen = {}
    )
}