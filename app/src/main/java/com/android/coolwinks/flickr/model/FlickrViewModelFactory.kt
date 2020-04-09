package com.android.coolwinks.flickr.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.coolwinks.flickr.viewmodel.FlickrViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class FlickrViewModelFactory(private val repository: FlickrRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlickrViewModel::class.java)) {

            return FlickrViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
