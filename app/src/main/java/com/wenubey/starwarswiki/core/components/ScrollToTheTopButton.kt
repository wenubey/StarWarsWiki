package com.wenubey.starwarswiki.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScrollToTheTopButton(
    showButton: Boolean,
    lazyListState: LazyListState,
    scope: CoroutineScope
) {
    AnimatedVisibility(
        visible = showButton,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier
            .padding(16.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .background(color = MaterialTheme.colorScheme.onTertiary)
                .padding(16.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = {
                        if (!lazyListState.isScrollInProgress) {
                            scope.launch {
                                lazyListState.animateScrollToItem(0)
                            }
                        }
                    }
                )
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                imageVector = Icons.Default.ArrowUpward,
                contentDescription = Constants.SCROLL_UP_DESC
            )
        }
    }
}