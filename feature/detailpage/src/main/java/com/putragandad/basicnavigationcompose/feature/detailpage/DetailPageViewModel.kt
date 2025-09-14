package com.putragandad.basicnavigationcompose.feature.detailpage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.putragandad.basicnavigationcompose.core.data.ComposeBasicRepository
import com.putragandad.basicnavigationcompose.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ComposeBasicRepository
) : ViewModel() {
    private val detailPlace = savedStateHandle.toRoute<DetailPageRoute>()

    val uiState: StateFlow<DetailPageUiState> =
        repository.getDetailTourismPlace(detailPlace.id)
            .map { res ->
                when (res) {
                    is Resource.Loading -> DetailPageUiState.Loading
                    is Resource.Success -> DetailPageUiState.Success(res.data)
                    is Resource.Error   -> DetailPageUiState.Error(res.message)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000), // start on first collector, stop 5s after last
                initialValue = DetailPageUiState.Loading
            )
}