package com.putragandad.basicnavigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.putragandad.basicnavigationcompose.core.ui.theme.BasicNavigationComposeTheme
import com.putragandad.basicnavigationcompose.feature.foryou.ForYouRoute
import com.putragandad.basicnavigationcompose.navigation.AppNavHost
import com.putragandad.basicnavigationcompose.navigation.LibraryRoute
import com.putragandad.basicnavigationcompose.navigation.SearchRoute
import com.putragandad.basicnavigationcompose.navigation.TopLevelDestination
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    rootNavController : NavHostController
) {
    val currentDestination = rootNavController.currentBackStackEntryAsState().value?.destination

    // set to remember to avoid rebuilt the list when recomposition happened
    val topLevelNavigationGraph = remember {
        listOf(
            TopLevelDestination.ForYou,
            TopLevelDestination.Search,
            TopLevelDestination.Library
        )
    }

    val topLevelNavigationScreen = remember {
        listOf(
            ForYouRoute,
            SearchRoute,
            LibraryRoute
        )
    }

    // show bottom bar only when current destination is on any top-level screen in each top-level graph
    val showBottomBar = topLevelNavigationScreen.any { screen ->
        currentDestination?.hierarchy?.any { it.hasRoute(screen::class) } == true
    }

    var visible by remember {
        mutableStateOf(true)
    }

    // using scaffold to ensure edge to edge
    Scaffold(
        modifier = modifier,
        // configuring bottom nav bar here
        bottomBar = {
            if(showBottomBar) {
                AnimatedVisibility(visible) {
                    NavigationBar() {
                        topLevelNavigationGraph.forEach { topLevelScreen ->
                            val isSelected =
                                currentDestination?.hierarchy?.any { it.hasRoute(topLevelScreen.route::class) } == true
                            NavigationBarItem(
                                icon = {
                                    if (isSelected) Icon(
                                        modifier = Modifier.size(24.dp),
                                        painter = painterResource(id = topLevelScreen.selectedIcon),
                                        contentDescription = topLevelScreen.name
                                    )
                                    else Icon(
                                        modifier = Modifier.size(24.dp),
                                        painter = painterResource(id = topLevelScreen.unselectedIcon),
                                        contentDescription = topLevelScreen.name
                                    )
                                },
                                label = { Text(topLevelScreen.name) },
                                selected = isSelected,
                                onClick = {
                                    rootNavController.navigate(topLevelScreen.route) {
                                        popUpTo(rootNavController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    ) { contentPadding ->
        // AppNavHost is our app navigation host, which host all route for our app, including bottom navigation bar
        AppNavHost(
            rootNavController = rootNavController,
            modifier = Modifier
                .padding(contentPadding)
        )
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            BasicNavigationComposeTheme {
                val navController = rememberNavController()

                // fill max size for the entire app
                MyApp(
                    modifier = Modifier.fillMaxSize(),
                    rootNavController = navController
                )
            }
        }

        window.isNavigationBarContrastEnforced = false
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyAppPreview() {
    BasicNavigationComposeTheme {

    }
}
