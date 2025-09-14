package com.putragandad.basicnavigationcompose.feature.foryou

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.putragandad.basicnavigationcompose.core.data.model.TourismListItem
import com.putragandad.basicnavigationcompose.core.ui.component.TourismPlaceCard
import com.putragandad.basicnavigationcompose.core.ui.theme.BasicNavigationComposeTheme

// composable screen for bottom nav bar screen
@Composable
fun ForYouScreen(
    modifier: Modifier = Modifier,
    viewModel: ForYouViewModel = viewModel(),
    onTourismPlaceItemClick : (Int) -> Unit
) {
    val uiState : ForYouUiState by viewModel.uiState.collectAsStateWithLifecycle()

    ForYouScreen(
        modifier = modifier,
        uiState = uiState,
        onTourismPlaceItemClick = onTourismPlaceItemClick
    )
}

@Composable
fun ForYouScreen(
    modifier: Modifier = Modifier,
    uiState: ForYouUiState,
    onTourismPlaceItemClick : (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when(uiState) {
            is ForYouUiState.Loading -> {

            }

            is ForYouUiState.Success -> {
                uiState.data?.let { tourismPlace ->
                    TourismPlaceList(
                        list = tourismPlace,
                        onItemClick = onTourismPlaceItemClick
                    )
                }
            }

            is ForYouUiState.Error -> {
                val message = uiState.message
            }
        }
    }
}

@Composable
fun TourismPlaceList(
    list: List<TourismListItem>,
    onItemClick : (Int) -> Unit
) {
    LazyColumn() {
        items(list, key = { it.id }) { item ->
            TourismPlaceCard(
                idPlace = item.id,
                placeName = item.name,
                onTourismPlaceClick = onItemClick // handle the onItemClick on the card
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SecondScreenPreview() {
    BasicNavigationComposeTheme {
        ForYouScreen(
            uiState = ForYouUiState.Success(emptyList()),
            onTourismPlaceItemClick = {}
        )
    }
}
