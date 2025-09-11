package com.putragandad.basicnavigationcompose.screens.toplevel.foryou

import androidx.lifecycle.ViewModel
import com.putragandad.basicnavigationcompose.data.source.remote.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): ViewModel() {
}