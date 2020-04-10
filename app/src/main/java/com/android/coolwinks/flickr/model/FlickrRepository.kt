package com.android.coolwinks.flickr.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.coolwinks.AppConstant.FLICKR_INITIAL_PAGE
import com.android.coolwinks.AppConstant.PAGE_SIZE
import com.android.coolwinks.AppConstant.PREFETCH_DISTANCE
import com.android.coolwinks.utils.RepoResult
import com.android.coolwinks.utils.TaskStatusResult
import javax.inject.Inject

class FlickrRepository @Inject constructor(
    private val flickrRemoteDataSource: FlickrRemoteDataSource,
    private val flickerLocalDataSource: FlickerLocalDataSource
) {

    private val pageListConfig by lazy {
        PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()
    }

   suspend fun getPhotos(): RepoResult<PagedList<Photo>> {

        val dataSourceFactory = flickerLocalDataSource.getPhotos()
        // Construct the boundary callback
        val boundaryCallback =
            FlickrBoundaryCondition(flickrRemoteDataSource, flickerLocalDataSource)
        val taskStatusLiveData = boundaryCallback.taskStatusLiveData

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, pageListConfig)
            .setBoundaryCallback(boundaryCallback)
            .build()

        // Get data and network errorsfrom boundary callback
        return RepoResult(data, taskStatusLiveData)
    }

    fun refreshPhotos(): LiveData<TaskStatusResult> {
        val data = MutableLiveData<TaskStatusResult>()
        flickrRemoteDataSource.getPhotoFromFlickrAPI(
            FLICKR_INITIAL_PAGE.toString(),
            PAGE_SIZE.toString(),
            {
                if (it.photo.isNotEmpty()){
                    flickerLocalDataSource.deletePhoto {
                        flickerLocalDataSource.insertPhoto(it.photo) {
                            data.postValue(TaskStatusResult.Success())
                        }
                    }

                }else{
                    data.postValue(TaskStatusResult.Error())
                }

            },
            {
                data.postValue(TaskStatusResult.Error())
            })
        return data
    }

}