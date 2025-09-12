package com.putragandad.basicnavigationcompose.screens.toplevel.foryou

import com.putragandad.basicnavigationcompose.screens.model.TourismListItem

sealed interface ForYouUiState {
    data object Loading : ForYouUiState

    data class Success(val data: List<TourismListItem>?) : ForYouUiState

    data class Error(val message: String?) : ForYouUiState
}