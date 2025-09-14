package com.putragandad.basicnavigationcompose.core.data.source.remote.network

import com.putragandad.basicnavigationcompose.core.data.source.remote.response.TourismApiResponse
import com.putragandad.basicnavigationcompose.core.data.source.remote.response.TourismDetailApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    suspend fun getTourismPlaceList(): TourismApiResponse

    @GET("detail/{id}")
    suspend fun getDetailTourismPlace(
        @Path("id") id: Int
    ) : TourismDetailApiResponse

}