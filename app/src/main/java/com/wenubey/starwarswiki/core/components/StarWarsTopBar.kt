@file:JvmName("StarWarsTopBarKt")

package com.wenubey.starwarswiki.core.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.core.Constants.TOP_BAR_HEIGHT

@Composable
fun StarWarsTopBar(
    navigateToBackScreen: (() -> Unit)? = null,
    lazyListState: LazyListState? = null,
) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .animateContentSize(animationSpec = tween(durationMillis = 300))
                .height(height = if (lazyListState != null && lazyListState.canScrollBackward) 0.dp else TOP_BAR_HEIGHT.dp)
        ) {
            if (navigateToBackScreen != null) {
                IconButton(
                    onClick = {
                        navigateToBackScreen.invoke()
                    }, modifier = Modifier.align(
                        Alignment.CenterStart
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = Constants.GO_BACK_PREVIOUS_SCREEN_DESC
                    )
                }
            }
            Text(
                text = Constants.TOP_BAR_TITLE,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                fontSize = 24.sp
            )
        }

}

@Preview
@Composable
fun StarWarsTopBarPreview() {
    StarWarsTopBar()
}