package com.wenubey.starwarswiki.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants

@Composable
fun ErrorScreen(
    paddingValues: PaddingValues,
    size: Dp = 250.dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .padding(paddingValues)
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            modifier = Modifier.size(size),
            contentDescription = Constants.FILM_OPENING_CRAWL_NOT_FOUND_DESC
        )
    }
}