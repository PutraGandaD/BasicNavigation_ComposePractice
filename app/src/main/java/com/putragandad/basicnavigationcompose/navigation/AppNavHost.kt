package com.putragandad.basicnavigationcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.putragandad.basicnavigationcompose.screens.auth.login.LoginScreen
import com.putragandad.basicnavigationcompose.screens.auth.register.RegisterScreen
import com.putragandad.basicnavigationcompose.screens.toplevel.foryou.ForYouScreen
import com.putragandad.basicnavigationcompose.screens.toplevel.library.LibraryScreen
import com.putragandad.basicnavigationcompose.screens.toplevel.search.SearchScreen
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable data object TopLevelGraph

// serializable used for Type-safe navigation
// serializable for Authentication Route (which is nested)
@Serializable data object AuthBaseGraph

// serializable for Route(child) inside Authentication Graph(parent/base)
@Serializable data object LoginRoute
@Serializable data object RegisterRoute

// serializable for Route(child) inside nested Top Level Destination graph
@Serializable data object ForYouRoute
@Serializable data object SearchRoute
@Serializable data object LibraryRoute

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
            composable<ForYouRoute> {
                ForYouScreen(modifier = modifier)
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