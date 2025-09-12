package com.putragandad.basicnavigationcompose.screens.toplevel.foryou.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putragandad.basicnavigationcompose.data.ComposeBasicRepository
import com.putragandad.basicnavigationcompose.screens.toplevel.foryou.state.ForYouUiState
import com.putragandad.basicnavigationcompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val repository: ComposeBasicRepository
): ViewModel() {
    val uiState: StateFlow<ForYouUiState> =
        repository.getTourismPlaceList()
            .map { res ->
                when (res) {
                    is Resource.Loading -> ForYouUiState.Loading
                    is Resource.Success -> ForYouUiState.Success(res.data)
                    is Resource.Error   -> ForYouUiState.Error(res.message)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Companion.WhileSubscribed(5_000), // start on first collector, stop 5s after last
                initialValue = ForYouUiState.Loading
            )
}