package com.wenubey.starwarswiki.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.domain.models.CharacterModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: CharacterModel?
) {
    Scaffold(
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = character?.name ?: UNDEFINED)
            }
        }
    )
}