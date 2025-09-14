package com.putragandad.basicnavigationcompose.core.data.source.remote

import com.putragandad.basicnavigationcompose.core.data.source.remote.network.ApiService
import com.putragandad.basicnavigationcompose.core.data.source.remote.response.TourismDetailApiResponse
import com.putragandad.basicnavigationcompose.core.data.source.remote.response.TourismItemApiResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTourismPlaceList() : List<TourismItemApiResponse> =
        apiService.getTourismPlaceList().places

    suspend fun getDetailTourismPlace(id: Int) : TourismItemApiResponse =
        apiService.getDetailTourismPlace(id).place
}