package com.wenubey.starwarswiki.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import com.google.gson.Gson
import com.wenubey.starwarswiki.core.parcelable
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.domain.models.CharacterModelNavType
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
                val json = Uri.encode(Gson().toJson(character))
                navHostController.navigate(Screen.CharacterDetailScreen.route + "/$json")

            })
        }
        composable(route = Screen.CharacterDetailScreen.route + "/{character}", arguments = listOf(
            navArgument("character") {
                type = CharacterModelNavType()
            }
        )) {
            val character = it.arguments?.parcelable<CharacterModel>("character")
            CharacterDetailScreen(character = character)
        }


    }
}