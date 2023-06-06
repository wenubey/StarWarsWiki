package com.wenubey.starwarswiki.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.starwarswiki.core.Constants.mockData
import com.wenubey.starwarswiki.core.components.CustomProgressBar
import com.wenubey.starwarswiki.core.components.StarWarsTopBar
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.FilmModel
import com.wenubey.starwarswiki.presentation.components.detail.BottomSheet
import com.wenubey.starwarswiki.presentation.components.detail.BottomSheetContent
import com.wenubey.starwarswiki.presentation.components.detail.CharacterDetailHeader
import com.wenubey.starwarswiki.presentation.components.detail.CharacterFilmList
import com.wenubey.starwarswiki.presentation.components.detail.CharacterImageAndSpecieRow
import com.wenubey.starwarswiki.presentation.components.detail.VehicleStarshipRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: CharacterModel?,
    navigateToFilmOpeningCrawl: (film: FilmModel) -> Unit,
    navigateToBackScreen: () -> Unit,
    showAppBar: Boolean
) {
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            initialValue = SheetValue.PartiallyExpanded,
            skipPartiallyExpanded = false
        )
    )
    val scope = rememberCoroutineScope()
    val bottomSheetContent =
        remember { mutableStateOf<BottomSheetContent>(BottomSheetContent.EmptyContent) }

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContainerColor = MaterialTheme.colorScheme.primaryContainer,
        sheetContent = {
            BottomSheet(scope = scope, sheetState = sheetState, bottomSheetContent = bottomSheetContent.value)
        },
        topBar = {
            StarWarsTopBar(
                navigateToBackScreen = navigateToBackScreen,
                showAppBar = showAppBar
            )
        },
        content = { paddingValues ->
            if (character != null) {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp), colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        CharacterDetailHeader(character = character)
                        CharacterImageAndSpecieRow(character = character)
                        CharacterFilmList(
                            filmsList = character.films,
                            navigateToFilmOpeningCrawl = navigateToFilmOpeningCrawl
                        )
                        VehicleStarshipRow(
                            character = character,
                            scope = scope,
                            bottomSheetContent = bottomSheetContent,
                            sheetState = sheetState
                        )
                    }
                }
            } else {
                CustomProgressBar()
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CharacterDetailScreenPreview() {
    CharacterDetailScreen(character = mockData, {}, {}, showAppBar = true)
}

