package com.wenubey.starwarswiki.presentation.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants
import com.wenubey.starwarswiki.domain.models.FilmModel

@Composable
fun CharacterFilmList(
    filmsList: List<FilmModel>?,
    navigateToFilmOpeningCrawl: (film: FilmModel) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Outlined.Movie,
            contentDescription = Constants.MOVIES_DESC
        )
        Text(text = Constants.MOVIES)
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        if (filmsList.isNullOrEmpty()) {
            item {
                Text(text = Constants.UNDEFINED)
            }
        } else {
            items(filmsList) { film ->
                CharacterFilmItem(
                    film = film,
                    onClick = {
                        navigateToFilmOpeningCrawl(film)
                    }
                )
            }
        }
    }
}