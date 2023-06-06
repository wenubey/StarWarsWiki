package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.domain.models.FilmModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterFilmItem(
    film: FilmModel,
    onClick: () -> Unit,
    isExpanded: Boolean
) {
    ElevatedCard(
        modifier = Modifier.padding(4.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {

            if (isExpanded) {
                Text(text = film.title ?: Constants.UNDEFINED)
                Text(text = film.releaseDate ?: Constants.UNDEFINED)
            } else {
                Text(text = film.title ?: Constants.UNDEFINED )
            }

        }
    }
}