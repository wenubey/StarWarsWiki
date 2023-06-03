package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.Constants.UNDEFINED_COLOR
import com.wenubey.starwarswiki.core.Constants.mockData

@Composable
fun CharacterColorContent(
    colors: List<Color>,
    desc: String
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (colors.isEmpty()) {
            ColorContent(colors = UNDEFINED_COLOR)
            Text(text = UNDEFINED)
        } else {
            ColorContent(colors = colors)
            Text(text = desc)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun CharacterColorContentPreview() {
    CharacterColorContent(colors = mockData.eyeColor, desc = "MOCK")
}

@Composable
fun ColorContent(
    colors: List<Color>,
) {
    if (colors.size > 1) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(25.dp)
                .background(Brush.horizontalGradient(colors, startX = 10f))
        )
    } else {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(25.dp)
                .background(colors.first())
        )
    }
}

