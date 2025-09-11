package com.putragandad.basicnavigationcompose.data.source.remote

import com.putragandad.basicnavigationcompose.data.source.remote.network.ApiService
import com.putragandad.basicnavigationcompose.data.source.remote.response.TourismItemApiResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTourismPlaceList() : List<TourismItemApiResponse> =
        apiService.getTourismPlaceList().places
}