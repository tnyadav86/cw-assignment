package com.android.coolwinks.flickr.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.android.coolwinks.flickr.model.FlickrRepository
import com.android.coolwinks.flickr.model.Photo
import com.android.coolwinks.utils.RepoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FlickrViewModel @Inject constructor(private val repository: FlickrRepository) : ViewModel() {
    private val flickrRepoResult = MutableLiveData<RepoResult<PagedList<Photo>>>()

    val photoListLivedata = flickrRepoResult.switchMap {
        it.data
    }
    val taskStatusLiveData = flickrRepoResult.switchMap {
        it.networkErrors
    }

    fun getPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getPhotos()
            flickrRepoResult.postValue(data)
        }

    }

    fun refreshPhotos() = repository.refreshPhotos()
}