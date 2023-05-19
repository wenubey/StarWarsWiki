package com.wenubey.starwarswiki.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants.FILM_OPENING_CRAWL_NOT_FOUND_DESC
import com.wenubey.starwarswiki.core.components.StarWarsTopBarWithBackButton
import com.wenubey.starwarswiki.domain.models.FilmModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpeningCrawlScreen(
    film: FilmModel?,
    navigateToBackScreen: () -> Unit,
) {
    var offsetY by remember { mutableStateOf(0.dp) }


    LaunchedEffect(Unit) {
        val animationSpec = keyframes {
            durationMillis = 4000
            0f at 4000
            0.25f at 3000
            0.50f at 2000
            0.75f at 1000
            1f at 0
        }

        val offsetAnim = Animatable(0f)

        offsetAnim.animateTo(
            targetValue = 0f,
            animationSpec = animationSpec
        ) {
            offsetY = (value * 500).dp
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            StarWarsTopBarWithBackButton(navigateToBackScreen = navigateToBackScreen)
        },
        content = { paddingValues ->

            if (film != null && !film.openingCrawl.isNullOrEmpty() && !film.title.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Gray,
                                    Color.Black
                                ),
                                tileMode = TileMode.Repeated
                            )
                        )
                        .padding(4.dp)
                        .offset(y = offsetY)
                        .wrapContentHeight(),
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
                            textAlign = TextAlign.Start,
                            color = Color.Yellow
                        )
                    }

                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = FILM_OPENING_CRAWL_NOT_FOUND_DESC
                    )
                }
            }

        }
    )

}

