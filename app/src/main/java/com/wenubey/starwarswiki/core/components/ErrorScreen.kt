package com.wenubey.starwarswiki.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants

@Composable
fun ErrorScreen(
    size: Dp = 250.dp,
) {
    Box(
        modifier = Modifier
            .size(size).padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            modifier = Modifier.size(size),
            contentDescription = Constants.FILM_OPENING_CRAWL_NOT_FOUND_DESC,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen()
}