package com.wenubey.starwarswiki.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants.UNDEFINED

@Composable
fun UndefinedBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.errorContainer),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = UNDEFINED,
            modifier = Modifier.padding(4.dp),
            style = TextStyle.Default.copy(
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        )
    }
}

@Preview
@Composable
fun UndefinedBoxPreview() {
    UndefinedBox()
}