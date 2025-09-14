package com.putragandad.basicnavigationcompose.feature.detailpage

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable data object DetailPageGraph // for base DetailPage navgraph

@Serializable data class DetailPageRoute(val id: Int) // for DetailPage screen

fun NavGraphBuilder.detailPageFeature(
    modifier: Modifier = Modifier
) {
    composable<DetailPageRoute> {
        val viewModel = hiltViewModel<DetailPageViewModel>()
        DetailPageScreen(
            modifier = modifier,
            viewModel = viewModel
        )
    }
}