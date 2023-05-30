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
    navigateToCharacterList: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(5000)
        navigateToCharacterList()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { StarWarsTopBar() },
        content = { paddingValues ->
            OpeningQuote(paddingValues = paddingValues)
        },
    )
}