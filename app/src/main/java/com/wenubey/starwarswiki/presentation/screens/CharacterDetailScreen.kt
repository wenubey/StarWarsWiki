package com.wenubey.starwarswiki.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
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
import com.wenubey.starwarswiki.presentation.components.detail.CharacterPhoto
import com.wenubey.starwarswiki.presentation.components.detail.CharacterSpecie
import com.wenubey.starwarswiki.presentation.components.detail.VehicleStarshipRow
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: CharacterModel?,
    navigateToFilmOpeningCrawl: (film: FilmModel) -> Unit,
    navigateToBackScreen: () -> Unit,
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
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    BottomSheetScaffold(
            scaffoldState = sheetState,
            sheetContainerColor = MaterialTheme.colorScheme.primaryContainer,
            sheetContent = {
                BottomSheet(
                    scope = scope,
                    sheetState = sheetState,
                    bottomSheetContent = bottomSheetContent.value,
                    isPortrait = isPortrait
                )
            },
            content = { paddingValues ->
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    if (character != null) {
                        if (isPortrait) {
                            CharacterDetailPortrait(
                                character = character,
                                scope = scope,
                                bottomSheetContent = bottomSheetContent,
                                sheetState = sheetState,
                                navigateToFilmOpeningCrawl = navigateToFilmOpeningCrawl,
                                navigateToBackScreen = navigateToBackScreen
                            )
                        } else {
                            CharacterDetailLandscape(
                                character = character,
                                scope = scope,
                                bottomSheetContent = bottomSheetContent,
                                sheetState = sheetState,
                                navigateToFilmOpeningCrawl = navigateToFilmOpeningCrawl,
                            )
                        }
                    } else {
                        CustomProgressBar()
                    }
                }
            }
        )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailLandscape(
    character: CharacterModel?,
    scope: CoroutineScope,
    bottomSheetContent: MutableState<BottomSheetContent>,
    sheetState: BottomSheetScaffoldState,
    navigateToFilmOpeningCrawl: (film: FilmModel) -> Unit,
) {
    if (character != null) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            CharacterDetailHeader(character = character)
            Row {
                CharacterPhoto(character = character, modifier = Modifier.weight(0.2f))
                CharacterSpecie(character = character, modifier = Modifier.weight(0.2f))
                Column(modifier = Modifier.weight(0.3f)) {
                    VehicleStarshipRow(
                        character = character,
                        scope = scope,
                        bottomSheetContent = bottomSheetContent,
                        sheetState = sheetState,
                        modifier = Modifier.weight(0.5f)
                    )
                    CharacterFilmList(
                        filmsList = character.films,
                        navigateToFilmOpeningCrawl = navigateToFilmOpeningCrawl,
                        modifier = Modifier.weight(0.3f),
                        isPortrait = false
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailPortrait(
    character: CharacterModel?,
    scope: CoroutineScope,
    bottomSheetContent: MutableState<BottomSheetContent>,
    sheetState: BottomSheetScaffoldState,
    navigateToFilmOpeningCrawl: (film: FilmModel) -> Unit,
    navigateToBackScreen: () -> Unit
) {
    if (character != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            StarWarsTopBar(
                navigateToBackScreen = navigateToBackScreen,
            )
            ElevatedCard(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp), colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                CharacterDetailHeader(character = character)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CharacterPhoto(character = character)
                    CharacterSpecie(character = character)
                }
                CharacterFilmList(
                    filmsList = character.films,
                    navigateToFilmOpeningCrawl = navigateToFilmOpeningCrawl,
                    isPortrait = true
                )
                VehicleStarshipRow(
                    character = character,
                    scope = scope,
                    bottomSheetContent = bottomSheetContent,
                    sheetState = sheetState
                )
            }
        }
    }
}

@Preview(
    showBackground = true, showSystemUi = true,
    device = "spec:width=393dp,height=808dp,dpi=480,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun CharacterDetailLandscapePreview() {
    CharacterDetailScreen(character = mockData, navigateToFilmOpeningCrawl = {}, {})
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.TABLET)
@Composable
fun CharacterDetailTabletPreview() {
    CharacterDetailScreen(character = mockData, navigateToFilmOpeningCrawl = {}, {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CharacterDetailPortraitPreview() {
    CharacterDetailScreen(character = mockData, {}, {})
}

