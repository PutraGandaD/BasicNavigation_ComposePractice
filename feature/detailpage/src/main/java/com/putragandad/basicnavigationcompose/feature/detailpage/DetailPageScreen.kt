package com.putragandad.basicnavigationcompose.feature.detailpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailPageScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailPageViewModel = viewModel()
) {
    val uiState: DetailPageUiState by viewModel.uiState.collectAsStateWithLifecycle()

    DetailPageScreen(
        modifier, uiState
    )
}

@Composable
fun DetailPageScreen(
    modifier: Modifier = Modifier,
    uiState: DetailPageUiState
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        when(uiState) {
            is DetailPageUiState.Loading -> {

            }

            is DetailPageUiState.Success -> {
                uiState.data?.let { data ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp) // spacing between texts
                    ) {
                        Text(
                            text = data.name,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = data.address,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = data.description,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = data.image,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            is DetailPageUiState.Error -> {
                val message = uiState.message
            }
        }
    }
}

