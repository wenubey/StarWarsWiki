package com.wenubey.starwarswiki.presentation.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wenubey.starwarswiki.core.Constants.TAG
import com.wenubey.starwarswiki.core.components.ErrorScreen
import com.wenubey.starwarswiki.core.components.StarWarsTopBarWithBackButton
import com.wenubey.starwarswiki.domain.models.FilmModel


@Composable
fun OpeningCrawlScreen(
    film: FilmModel?,
    navigateToBackScreen: () -> Unit,
) {

    var offsetY by remember { mutableStateOf(100.dp) }
    var boxVisibility by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val assetManager = context.assets

    LaunchedEffect(Unit) {
        val animationSpec = keyframes {
            durationMillis = 24000
            1f at 0
            0.50f at 4000
            0f at 8000
            -0.5f at 12000
            -1f at 16000
            -1.5f at 20000
            -2f at 24000
        }
        val offsetAnim = Animatable(1f)
        offsetAnim.animateTo(
            targetValue = -2f,
            animationSpec = animationSpec,
        ) {
            offsetY = (value * 500).dp
            Log.i(TAG, "OpeningCrawlScreen: value: $value")
        }
        boxVisibility = false
    }

    val imgFile = assetManager.open("Starfield.png")
    val imgBitmap: Bitmap = BitmapFactory.decodeStream(imgFile)

    Scaffold(
        modifier = Modifier,
        topBar = {
            StarWarsTopBarWithBackButton(navigateToBackScreen = navigateToBackScreen)
        },
        content = { paddingValues ->
            AsyncImage(
                model = imgBitmap,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            if (film != null && !film.openingCrawl.isNullOrEmpty() && !film.title.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            rotationX = 15f
                            transformOrigin = TransformOrigin(0.5f, 0.8f)
                            scaleY = 2f
                        }
                        .offset(y = offsetY),
                    contentAlignment = Alignment.TopCenter
                ) {
                    AnimatedVisibility(
                        visible = boxVisibility,
                        exit = shrinkVertically()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = film.title,
                                fontSize = 36.sp,
                                color = Color.Yellow,
                                textAlign = TextAlign.Center,
                                style = TextStyle.Default.copy(
                                    letterSpacing = 2.sp
                                )
                            )
                            Text(
                                text = film.openingCrawl,
                                modifier = Modifier
                                    .padding(16.dp),
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Yellow
                            )
                        }
                    }
                }

            } else {
                ErrorScreen(paddingValues = paddingValues)
            }

        }
    )

}

