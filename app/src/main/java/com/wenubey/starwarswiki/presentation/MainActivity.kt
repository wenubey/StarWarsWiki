package com.wenubey.starwarswiki.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.wenubey.starwarswiki.presentation.navigation.NavGraph
import com.wenubey.starwarswiki.presentation.ui.theme.StarWarsWikiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsWikiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    navHostController = rememberNavController()
                    val viewModel = hiltViewModel<StarWarsViewModel>()
                    val searchQuery = viewModel.searchQuery.collectAsState()
                    val characters = viewModel.characterPagingFlow.collectAsLazyPagingItems()
                    val windowSizeClass = calculateWindowSizeClass(activity = this)
                    val showAppBar = windowSizeClass.heightSizeClass != WindowHeightSizeClass.Compact


                    NavGraph(
                        navHostController = navHostController,
                        characters = characters,
                        searchQuery = searchQuery,
                        setSearchQuery = viewModel::setSearchQuery,
                        showAppBar = showAppBar
                    )
                }
            }
        }
    }
}

