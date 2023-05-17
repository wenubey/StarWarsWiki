package com.wenubey.starwarswiki.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.R
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.core.getFirstOrNull
import com.wenubey.starwarswiki.domain.models.CharacterModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: CharacterModel?
) {
    Scaffold(
        content = { paddingValues ->
            if (character != null) {
                Column(
                    modifier = Modifier.padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(text = character.name)
                            Text(text = character.species.getFirstOrNull())
                        }
                        Image(
                            painter = painterResource(id = R.drawable.progressbarindicator),
                            contentDescription = "Character Image",
                        )
                    }
                }
            } else {
                CustomProgressBar()
            }

        }
    )
}

@Preview(showBackground = false)
@Composable
fun CharacterDetailScreenPreview() {
    CharacterDetailScreen(character = mockData)
}