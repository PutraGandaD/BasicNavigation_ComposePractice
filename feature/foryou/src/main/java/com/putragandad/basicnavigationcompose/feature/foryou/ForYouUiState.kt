package com.putragandad.basicnavigationcompose.feature.foryou

import com.putragandad.basicnavigationcompose.core.data.model.TourismListItem

sealed interface ForYouUiState {
    data object Loading : ForYouUiState

    data class Success(val data: List<TourismListItem>?) : ForYouUiState

    data class Error(val message: String?) : ForYouUiState
}