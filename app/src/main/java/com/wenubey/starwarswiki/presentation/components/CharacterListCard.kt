package com.wenubey.starwarswiki.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.getFirstOrNull
import com.wenubey.starwarswiki.domain.models.CharacterModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListCard(
    navigateToDetailScreen: () -> Unit,
    character: CharacterModel
) {
    ElevatedCard(
        onClick = navigateToDetailScreen,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(text = character.name)
            Text(text = character.species.getFirstOrNull())
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CharacterListCardPreview() {
        CharacterListCard(character = mockData, navigateToDetailScreen = {})
}