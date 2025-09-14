package com.putragandad.basicnavigationcompose.feature.detailpage

import com.putragandad.basicnavigationcompose.core.data.model.TourismItem

sealed interface DetailPageUiState {
    data object Loading: DetailPageUiState

    data class Success(val data: TourismItem?) : DetailPageUiState

    data class Error(val message: String?) : DetailPageUiState
}