package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.second

@Composable
fun CharacterEyeContent(
    eyeColors: List<Color>,
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EyeContent(eyeColors = eyeColors)
        Text(text = "Eye Color")
    }

}

@Preview(showBackground = true)
@Composable
fun CharacterEyeContentPreview() {
    CharacterEyeContent(eyeColors = mockData.eyeColor)
}

@Composable
fun EyeContent(
    eyeColors: List<Color>,
) {
    Canvas(modifier = Modifier.size(25.dp)) {
        val ovalSize = Size(size.width / 2 , size.height / 2)
        val eyePupilOffset = Offset(x = size.height / 4, y = size.width / 4)
        val startAngle = 90f
        val sweepAngle = 180f
        if (eyeColors.size > 1) {

            drawArc(
                color = eyeColors.first(),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = eyePupilOffset,
                size = ovalSize
            )

            drawArc(
                color = eyeColors.second(),
                startAngle = startAngle + 180f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = eyePupilOffset,
                size = ovalSize
            )
        } else {
            drawOval(
                color = eyeColors.first(),
                topLeft = eyePupilOffset,
                size = ovalSize
            )
        }


    }
}