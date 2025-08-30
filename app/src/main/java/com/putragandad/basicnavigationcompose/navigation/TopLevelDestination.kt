package com.putragandad.basicnavigationcompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    FOR_YOU_SCREEN("foryou", "For You", Icons.Default.Home, "For You"),
    SEARCH_SCREEN("search", "Search", Icons.Default.Search, "Search"),
    LIBRARY_SCREEN("library", "Library", Icons.Default.Person, "Library")
}