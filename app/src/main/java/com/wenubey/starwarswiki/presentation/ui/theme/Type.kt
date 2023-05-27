package com.wenubey.starwarswiki.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wenubey.starwarswiki.R


val gothicA1Font = FontFamily(
    listOf(
        Font(R.font.gothic_a1_black),
        Font(R.font.gothic_a1_bold),
        Font(R.font.gothic_a1_extra_bold),
        Font(R.font.gothic_a1_extra_light),
        Font(R.font.gothic_a1_light),
        Font(R.font.gothic_a1_medium),
        Font(R.font.gothic_a1_regular),
        Font(R.font.gothic_a1_semi_bold),
    )
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = gothicA1Font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)