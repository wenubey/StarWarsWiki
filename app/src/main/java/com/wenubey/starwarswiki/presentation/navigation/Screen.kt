package com.wenubey.starwarswiki.presentation.navigation

sealed class Screen(val route: String) {

    object OpeningQuoteScreen: Screen("openingQuoteScreen")

    object CharacterListScreen: Screen("characterListScreen")

    object CharacterDetailScreen: Screen("characterDetailScreen")

    object OpeningCrawlScreen: Screen("openingCrawlScreen")
}
