package com.wenubey.starwarswiki.presentation.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants.BACKGROUND_IMAGE_DESC
import com.wenubey.starwarswiki.core.components.ErrorScreen
import com.wenubey.starwarswiki.core.components.StarWarsTopBarWithBackButton
import com.wenubey.starwarswiki.domain.models.FilmModel
import com.wenubey.starwarswiki.presentation.ui.theme.OpeningCrawlColor


@Composable
fun OpeningCrawlScreen(
    film: FilmModel?,
    navigateToBackScreen: () -> Unit,
) {

    var offsetY by remember { mutableStateOf(100.dp) }
    var boxVisibility by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val assetManager = context.assets
    val imgFile = assetManager.open("star_field.png")
    val imgBitmap: Bitmap = BitmapFactory.decodeStream(imgFile)

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
        }
        boxVisibility = false
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            StarWarsTopBarWithBackButton(navigateToBackScreen = navigateToBackScreen)
        },
        content = { paddingValues ->
            Image(
                bitmap = imgBitmap.asImageBitmap(),
                contentDescription = BACKGROUND_IMAGE_DESC,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            if (film != null && !film.openingCrawl.isNullOrEmpty() && !film.title.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
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
                                color = OpeningCrawlColor,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineLarge
                            )
                            Text(
                                text = film.openingCrawl,
                                textAlign = TextAlign.Center,
                                color = OpeningCrawlColor,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

            } else {
                ErrorScreen()
            }

        }
    )

}

