package com.putragandad.basicnavigationcompose.core.data

import com.putragandad.basicnavigationcompose.core.data.model.TourismListItem
import com.putragandad.basicnavigationcompose.core.data.source.remote.RemoteDataSource
import com.putragandad.basicnavigationcompose.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ComposeBasicRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
     fun getTourismPlaceList() : Flow<Resource<List<TourismListItem>>> = flow {
        emit(Resource.Loading())
        try {
            val getList = remoteDataSource.getTourismPlaceList().map {
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
}