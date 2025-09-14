package com.putragandad.basicnavigationcompose.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TourismItemApiResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("longitude")
    val longitude: Double,

    @field:SerializedName("latitude")
    val latitude: Double,

    @field:SerializedName("like")
    val like: Int,

    @field:SerializedName("image")
    val image: String
)