package com.putragandad.basicnavigationcompose.feature.foryou

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable data object ForYouGraph // for base ForYou navgraph

@Serializable data object ForYouRoute // for ForYou screen (parent)

fun NavGraphBuilder.forYouSection(
    modifier: Modifier = Modifier,
    onTourismPlaceItemClick: (Int) -> Unit,
    detailPageScreenDestination: NavGraphBuilder.() -> Unit // for composable function connecting to detail page in different module
) {
    navigation<ForYouGraph>(startDestination = ForYouRoute) {
        // composable ForYou screen
        composable<ForYouRoute> { backStackEntry ->
            // create viewmodel for this current backstackentry
            val viewModel = hiltViewModel<ForYouViewModel>()
            ForYouScreen(
                modifier = modifier,
                viewModel = viewModel,
                onTourismPlaceItemClick = { id ->
                    onTourismPlaceItemClick(id)
                }
            )
        }

        // composable DetailPageScreen
        // since DetailPageScreen is in another module, to "connect" it to this module,
        // the connection managed through :app module which is the central module of our app.
        // so we're exposing the DetailPage composable to the lambda in the parameter of this NavGraphBuilder
        detailPageScreenDestination()
    }
}