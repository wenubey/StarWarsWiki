package com.wenubey.starwarswiki.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

class ScreenSize {
    @Composable
    fun height(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenHeightDp
    }
    @Composable
    fun width(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp
    }
}