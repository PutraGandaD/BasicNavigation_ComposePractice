package com.putragandad.basicnavigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.toRoute
import com.putragandad.basicnavigationcompose.ui.theme.BasicNavigationComposeTheme
import kotlinx.serialization.Serializable

// Serializable for normal screen (non bottom navigation bar)
// for route and route argument
@Serializable
data class ArtistDetail(val name: String)

@Serializable
data class AlbumDetail(val name: String)

// composable screen for bottom nav bar screen
@Composable
fun ForYouScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(), // inherit from main modifier to fill max size and edge to edge
        contentAlignment = Alignment.Center
    ) {
        Text("For You Screen")
    }
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Search Screen")
    }
}

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Library Screen")
    }
}

// enum for list of bottom nav bar menu
enum class BottomNavBarDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    FOR_YOU_SCREEN("foryou", "For You", Icons.Default.Home, "For You"),
    SEARCH_SCREEN("search", "Search", Icons.Default.Search, "Search"),
    LIBRARY_SCREEN("library", "Library", Icons.Default.Person, "Library")
}

// main app navigation host, which also responsible for indexing bottom app bar destination
@Composable
fun MainAppNavHost(
    navController: NavHostController,
    startDestination: BottomNavBarDestination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        BottomNavBarDestination.entries.forEach { destination ->
            composable(destination.route) {
                when(destination) {
                    BottomNavBarDestination.FOR_YOU_SCREEN -> ForYouScreen()
                    BottomNavBarDestination.SEARCH_SCREEN -> SearchScreen()
                    BottomNavBarDestination.LIBRARY_SCREEN -> LibraryScreen()
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = BottomNavBarDestination.FOR_YOU_SCREEN
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = modifier,
        // configuring bottom nav bar here
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                BottomNavBarDestination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        MainAppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))
    }
}

@Composable
fun ArtistDetailScreen(
    name: String,
    modifier: Modifier = Modifier,
    onNavigateToSecondScreen: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Hello, $name. It's me.",
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                onNavigateToSecondScreen()
            }
        ) {
            Text("I was wondering...")
        }
    }
}

@Composable
fun AlbumDetailScreen(
    name: String,
    modifier: Modifier = Modifier,
    onNavigateToFirstScreen: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "After all these years, would you like to meet me, $name?",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                onNavigateToFirstScreen()
            }
        ) {
            Text("No. (Back)")
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicNavigationComposeTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    BasicNavigationComposeTheme {

    }
}