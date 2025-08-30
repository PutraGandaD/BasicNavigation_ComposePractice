package com.putragandad.basicnavigationcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.putragandad.basicnavigationcompose.screens.foryou.ForYouScreen
import com.putragandad.basicnavigationcompose.screens.library.LibraryScreen
import com.putragandad.basicnavigationcompose.screens.search.SearchScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: TopLevelDestination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        TopLevelDestination.entries.forEach { destination ->
            composable(destination.route) {
                when(destination) {
                    TopLevelDestination.FOR_YOU_SCREEN -> ForYouScreen()
                    TopLevelDestination.SEARCH_SCREEN -> SearchScreen()
                    TopLevelDestination.LIBRARY_SCREEN -> LibraryScreen()
                }
            }
        }
    }
}