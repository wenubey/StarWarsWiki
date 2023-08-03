package com.wenubey.starwarswiki.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ThemeModeSwitch(
    modifier : Modifier = Modifier,
    checked: Boolean = false,
    onCheckedChanged: (checked:Boolean) -> Unit = {},
) {
    Switch(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChanged,
        thumbContent = {
                Icon(imageVector = if (checked) Icons.Default.DarkMode else Icons.Default.LightMode, contentDescription = "")
        },

    )
}

@Preview
@Composable
fun ThemeModeSwitchPreview() {
    ThemeModeSwitch()
}