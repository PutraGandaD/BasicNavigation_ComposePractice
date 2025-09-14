package com.putragandad.basicnavigationcompose.core.data

import com.putragandad.basicnavigationcompose.core.data.model.TourismItem
import com.putragandad.basicnavigationcompose.core.data.model.TourismListItem
import com.putragandad.basicnavigationcompose.core.data.source.remote.RemoteDataSource
import com.putragandad.basicnavigationcompose.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.String

class ComposeBasicRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
     fun getTourismPlaceList() : Flow<Resource<List<TourismListItem>>> = flow {
        emit(Resource.Loading())
        try {
            val getList = remoteDataSource.getTourismPlaceList().map {
                // map each item in the list to ui layer
                TourismListItem(
                    id = it.id,
                    name = it.name,
                    address = it.address,
                    image = it.image
                )
            }
            emit(Resource.Success(getList))
        } catch (t: Throwable) {
            emit(Resource.Error(t.message.toString()))
        }
    }

    fun getDetailTourismPlace(id: Int) : Flow<Resource<TourismItem>> = flow {
        emit(Resource.Loading())
        try {
            val getDetailPlace = remoteDataSource.getDetailTourismPlace(id)

            // map to ui layer
            val placeDetails = TourismItem(
                id = getDetailPlace.id,
                name = getDetailPlace.name,
                description = getDetailPlace.description,
                address = getDetailPlace.address,
                image = getDetailPlace.image
            )

            // then emit the final mapped result
            emit(Resource.Success(placeDetails))
        } catch (t: Throwable) {
            emit(Resource.Error(t.message.toString()))
        }
    }
}