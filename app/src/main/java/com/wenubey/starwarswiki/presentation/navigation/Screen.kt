package com.wenubey.starwarswiki.presentation.navigation

sealed class Screen(val route: String) {

    object CharacterListScreen: Screen("characterListScreen")

    object CharacterDetailScreen: Screen("characterDetailScreen")

    object OpeningCrawlScreen: Screen("openingCrawlScreen")
}
