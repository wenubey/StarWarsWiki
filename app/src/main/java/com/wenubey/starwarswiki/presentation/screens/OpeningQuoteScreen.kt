package com.wenubey.starwarswiki.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.wenubey.starwarswiki.core.components.OpeningQuote
import com.wenubey.starwarswiki.core.components.StarWarsTopBar
import kotlinx.coroutines.delay

@Composable
fun OpeningQuoteScreen(
    navigateToCharacterList: () -> Unit,
    checked: Boolean,
    onCheckedChanged: (checked: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        delay(5000)
        navigateToCharacterList()
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { StarWarsTopBar(
            checked = checked,
            onCheckedChanged = onCheckedChanged
        ) },
        content = { paddingValues ->
            OpeningQuote(paddingValues = paddingValues)
        },
    )
}