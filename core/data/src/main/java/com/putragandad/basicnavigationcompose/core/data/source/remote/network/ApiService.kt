package com.putragandad.basicnavigationcompose.core.data.source.remote.network

import com.putragandad.basicnavigationcompose.core.data.source.remote.response.TourismApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getTourismPlaceList(): TourismApiResponse


}