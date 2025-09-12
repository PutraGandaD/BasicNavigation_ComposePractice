package com.putragandad.basicnavigationcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.putragandad.basicnavigationcompose.screens.auth.login.LoginScreen
import com.putragandad.basicnavigationcompose.screens.auth.register.RegisterScreen
import com.putragandad.basicnavigationcompose.screens.toplevel.foryou.ForYouScreen
import com.putragandad.basicnavigationcompose.screens.toplevel.foryou.viewmodel.ForYouViewModel
import com.putragandad.basicnavigationcompose.screens.toplevel.library.LibraryScreen
import com.putragandad.basicnavigationcompose.screens.toplevel.search.SearchScreen
import kotlinx.serialization.Serializable
import java.io.Serial

@Composable
fun AppNavHost(
    rootNavController: NavHostController,
    modifier: Modifier = Modifier
) {
    // logic start destination based on login status
    NavHost(
        rootNavController,
        startDestination = ForYouGraph // and then set it here
    ) {
        navigation<ForYouGraph>(startDestination = ForYouRoute) {
            composable<ForYouRoute> { backStackEntry ->
                // create viewmodel for this current backstackentry
                val viewModel = hiltViewModel<ForYouViewModel>()
                ForYouScreen(
                    modifier = modifier,
                    viewModel = viewModel,
                    onTourismPlaceItemClick = { id ->
                        rootNavController.navigate(
                            route = DetailPlaceRoute(id)
                        )
                    }
                )
            }

            composable<DetailPlaceRoute> { backStackEntry ->

            }
        }

        navigation<SearchGraph>(startDestination = SearchRoute) {
            composable<SearchRoute> {
                SearchScreen(modifier = modifier)
            }
        }

        navigation<LibraryGraph>(startDestination = LibraryRoute) {
            composable<LibraryRoute> {
                LibraryScreen(modifier = modifier)
            }
        }

//        // authentication nested navigation route
//        navigation<AuthBaseGraph>(startDestination = LoginRoute) {
//            composable<LoginRoute> {
//                LoginScreen(
//                    modifier = modifier,
//                    onLoginSuccess = {
//                        rootNavController.navigate(ForYouRoute) {
//                            // remove the entire auth graph from back stack and go to for you graph
//                            popUpTo<AuthBaseGraph> { inclusive = true }
//                        }
//                    },
//                    navigateToRegisterScreen = {
//                        rootNavController.navigate(RegisterRoute)
//                    }
//                )
//            }
//
//            composable<RegisterRoute> {
//                RegisterScreen(
//                    modifier = modifier,
//                    onRegisterSuccess = {
//                        rootNavController.popBackStack() // navigate back to login screen
//                    }
//                )
//            }
//        }
    }
}