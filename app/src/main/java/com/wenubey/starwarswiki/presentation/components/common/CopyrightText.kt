package com.wenubey.starwarswiki.core.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.wenubey.starwarswiki.core.Constants.COPYRIGHT_FIFTH
import com.wenubey.starwarswiki.core.Constants.COPYRIGHT_FIRST
import com.wenubey.starwarswiki.core.Constants.COPYRIGHT_FOURTH
import com.wenubey.starwarswiki.core.Constants.COPYRIGHT_SECOND
import com.wenubey.starwarswiki.core.Constants.COPYRIGHT_THIRD
import com.wenubey.starwarswiki.core.Constants.DISNEY
import com.wenubey.starwarswiki.core.Constants.DISNEY_ADDRESS
import com.wenubey.starwarswiki.core.Constants.LUCASFILM
import com.wenubey.starwarswiki.core.Constants.LUCASFILM_ADDRESS
import com.wenubey.starwarswiki.presentation.ui.theme.OpeningQuoteColor

@Composable
fun CopyrightText(uriHandler: UriHandler) {
    val annotatedString = buildAnnotatedString {
        append(COPYRIGHT_FIRST)
        pushStringAnnotation(tag = LUCASFILM, annotation = LUCASFILM_ADDRESS)
        withStyle(style = SpanStyle(color = OpeningQuoteColor)) {
            append(COPYRIGHT_SECOND)
        }
        pop()
        append(COPYRIGHT_THIRD)
        pushStringAnnotation(tag = DISNEY, annotation = DISNEY_ADDRESS)
        withStyle(style = SpanStyle(color = OpeningQuoteColor)) {
            append(COPYRIGHT_FOURTH)
        }
        pop()
        append(COPYRIGHT_FIFTH)
    }
    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString
                .getStringAnnotations(LUCASFILM, offset, offset)
                .firstOrNull()?.let { link ->
                    uriHandler.openUri(link.item)
                }
            annotatedString
                .getStringAnnotations(DISNEY, offset, offset)
                .firstOrNull()?.let { link ->
                    uriHandler.openUri(link.item)
                }

        },
        style = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}