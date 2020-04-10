package com.android.coolwinks.flickr.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.android.coolwinks.AppConstant
import com.android.coolwinks.AppConstant.PAGE_SIZE
import com.android.coolwinks.utils.TaskStatusResult

class FlickrBoundaryCondition(
    private val flickrRemoteDataSource: FlickrRemoteDataSource,
    private val flickerLocalDataSource: FlickerLocalDataSource
) : PagedList.BoundaryCallback<Photo>() {

    private var lastRequestedPage = AppConstant.FLICKR_INITIAL_PAGE

    val _taskStatusLiveData = MutableLiveData<TaskStatusResult>()
    // LiveData of network errors.
    val taskStatusLiveData: LiveData<TaskStatusResult>
        get() = _taskStatusLiveData


    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        if (isRequestInProgress) return
        isRequestInProgress = true
        _taskStatusLiveData.postValue(TaskStatusResult.Loading())
        flickrRemoteDataSource.getPhotoFromFlickrAPI(
            lastRequestedPage.toString(),
            PAGE_SIZE.toString(),
            {
                lastRequestedPage = it.page
                flickerLocalDataSource.insertPhoto(it.photo) {
                    _taskStatusLiveData.postValue(TaskStatusResult.Success())
                    isRequestInProgress = false
                }
            },
            {
                isRequestInProgress = false
                _taskStatusLiveData.postValue(TaskStatusResult.Error(it))
            })

    }

    override fun onItemAtEndLoaded(itemAtEnd: Photo) {
        if (isRequestInProgress) return
        isRequestInProgress = true
        lastRequestedPage++
        flickrRemoteDataSource.getPhotoFromFlickrAPI(
            lastRequestedPage.toString(),
            PAGE_SIZE.toString(),
            {
                lastRequestedPage = it.page
                flickerLocalDataSource.insertPhoto(it.photo) {
                    isRequestInProgress = false
                    _taskStatusLiveData.postValue(TaskStatusResult.Success())
                }
            },
            {
                isRequestInProgress = false
                _taskStatusLiveData.postValue(TaskStatusResult.Error(it))
            })
    }

}
