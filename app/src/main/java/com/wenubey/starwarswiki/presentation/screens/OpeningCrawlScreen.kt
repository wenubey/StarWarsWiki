package com.wenubey.starwarswiki.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun OpeningCrawlScreen(
    openingCrawl: String?,
    navController: NavController
) {
    val yTranslation = remember { Animatable(0f) }
    val xTranslation = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }
    val showButton = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        with(yTranslation) {
            animateTo(-5000f, animationSpec = tween(durationMillis = 20000, easing = LinearEasing))
        }
        with(xTranslation) {
            animateTo(500f, animationSpec = tween(durationMillis = 20000, easing = LinearEasing))
        }
        with(scale) {
            animateTo(1.5f, animationSpec = tween(durationMillis = 20000, easing = LinearEasing))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value,
                translationY = yTranslation.value,
                translationX = xTranslation.value
            )
    ) {
        Text(
            text = openingCrawl!!,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterStart),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 32.sp,
                lineHeight = 40.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )
        )
    }
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            Text("Go Back")
        }

}
