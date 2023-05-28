package com.wenubey.starwarswiki.core.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.presentation.ui.theme.OpeningQuoteColor

@Composable
fun OpeningQuote(
    paddingValues: PaddingValues = PaddingValues(1.dp)
) {
    val context = LocalContext.current
    val assetManager = context.assets
    val imgFile = assetManager.open("star_field.png")
    val imgBitmap: Bitmap = BitmapFactory.decodeStream(imgFile)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = imgBitmap.asImageBitmap(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(paddingValues).padding(8.dp)
        ) {
            Text(
                text = "A long time ago in a galaxy far,",
                color = OpeningQuoteColor,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineMedium
            )
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = "far away",
                    color = OpeningQuoteColor,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.width(2.dp))
                DotsFlashing()
            }

        }
    }
}

@Preview
@Composable
fun OpeningQuotePreview() {
    OpeningQuote()
}