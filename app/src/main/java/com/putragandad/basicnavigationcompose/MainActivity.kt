package com.putragandad.basicnavigationcompose

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.putragandad.basicnavigationcompose.core.ui.theme.BasicNavigationComposeTheme
import com.putragandad.basicnavigationcompose.navigation.AppNavHost
import com.putragandad.basicnavigationcompose.navigation.TopLevelDestination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    rootNavController : NavHostController
) {
    val currentDestination = rootNavController.currentBackStackEntryAsState().value?.destination

    // set to remember to avoid rebuilt the list when recomposition happened
    val bottomScreens = remember {
        listOf(
            TopLevelDestination.ForYou,
            TopLevelDestination.Search,
            TopLevelDestination.Library
        )
    }

    // show bottom bar only when current destination is any top-level graph
    val showBottomBar = bottomScreens.any { screen ->
        currentDestination?.hierarchy?.any { it.hasRoute(screen.route::class) } == true
    }

    // using scaffold to ensure edge to edge
    Scaffold(
        modifier = modifier,
        // configuring bottom nav bar here
        bottomBar = {
            if(showBottomBar) {
                NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                    bottomScreens.forEach { screen ->
                        val isSelected =
                            currentDestination?.hierarchy?.any { it.hasRoute(screen.route::class) } == true
                        NavigationBarItem(
                            icon = {
                                if (isSelected) Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(id = screen.selectedIcon),
                                    contentDescription = screen.name
                                )
                                else Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(id = screen.unselectedIcon),
                                    contentDescription = screen.name
                                )
                            },
                            label = { Text(screen.name) },
                            selected = isSelected,
                            onClick = {
                                rootNavController.navigate(screen.route) {
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
    ) { contentPadding ->
        // AppNavHost is our app navigation host, which host all route for our app, including bottom navigation bar
        AppNavHost(
            rootNavController = rootNavController,
            modifier = Modifier.padding(contentPadding)
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
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    BasicNavigationComposeTheme {

    }
}