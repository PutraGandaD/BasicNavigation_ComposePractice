package com.putragandad.basicnavigationcompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.putragandad.basicnavigationcompose.R
import com.putragandad.basicnavigationcompose.feature.foryou.ForYouGraph
import kotlinx.serialization.Serializable

@Serializable
sealed class TopLevelDestination<T>(
    val name: String, // the name of the tab
    val selectedIcon: Int, // filled icon when selected
    val unselectedIcon: Int, // unfilled icon when not selected
    val route: T //graphs defined in feature module for each tab
) {
    @Serializable
    data object ForYou : TopLevelDestination<ForYouGraph>(
        name = "For You",
        unselectedIcon = R.drawable.ic_for_you_unselected,
        selectedIcon = R.drawable.ic_for_you_selected,
        route = ForYouGraph
    )

    @Serializable
    data object Search : TopLevelDestination<SearchGraph>(
        name = "Search",
        unselectedIcon = R.drawable.ic_search_24,
        selectedIcon = R.drawable.ic_search_24,
        route = SearchGraph
    )

    @Serializable
    data object Library : TopLevelDestination<LibraryGraph>(
        name = "Library",
        unselectedIcon = R.drawable.ic_library_unselected,
        selectedIcon = R.drawable.ic_library_selected,
        route = LibraryGraph
    )

}