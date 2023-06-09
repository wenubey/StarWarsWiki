package com.wenubey.starwarswiki.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.wenubey.starwarswiki.R


private val GothicA1Font = FontFamily(
    listOf(
        Font(R.font.gothic_a1_black),
        Font(R.font.gothic_a1_bold, weight = FontWeight.Bold),
        Font(R.font.gothic_a1_extra_bold, weight = FontWeight.ExtraBold),
        Font(R.font.gothic_a1_extra_light),
        Font(R.font.gothic_a1_light),
        Font(R.font.gothic_a1_medium, FontWeight.W500),
        Font(R.font.gothic_a1_regular),
        Font(R.font.gothic_a1_semi_bold),
    )
)

@Suppress("DEPRECATION")
val defaultTextStyle = TextStyle(
    fontFamily = GothicA1Font,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

// Set of Material typography styles to start with
val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = GothicA1Font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = GothicA1Font,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = GothicA1Font,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 36.sp,
        letterSpacing = 1.sp
    ),
    headlineMedium = defaultTextStyle.copy(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        lineBreak = LineBreak.Heading
    )
//    TextStyle(
//        fontFamily = GothicA1Font,
//        fontWeight = FontWeight.SemiBold,
//        fontSize = 20.sp,
//        lineHeight = 20.sp,
//        letterSpacing = 1.sp
//    )
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