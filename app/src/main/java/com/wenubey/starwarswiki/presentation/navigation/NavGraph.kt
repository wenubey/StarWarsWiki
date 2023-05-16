package com.wenubey.starwarswiki.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import com.wenubey.starwarswiki.core.parcelable
import com.wenubey.starwarswiki.domain.models.CharacterDetailArgs
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.presentation.screens.CharacterDetailScreen
import com.wenubey.starwarswiki.presentation.screens.CharacterListScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    characters: LazyPagingItems<CharacterModel>
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.CharacterListScreen.route
    ) {
        composable(route = Screen.CharacterListScreen.route) {
            CharacterListScreen(characters = characters, navigateToDetailScreen = { character ->
                navHostController.navigate(Screen.CharacterDetailScreen.route)
            })
        }
        composable(route = Screen.CharacterDetailScreen.route + "/{character}") { navBackStackEntry ->
            val args = navBackStackEntry.arguments?.parcelable<CharacterDetailArgs>("character")
            val character = args?.character
            CharacterDetailScreen(character = character)
        }


    }
}