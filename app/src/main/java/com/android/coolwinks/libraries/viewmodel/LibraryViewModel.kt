package com.android.coolwinks.libraries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.coolwinks.libraries.model.LibraryRepository
import com.android.coolwinks.libraries.model.UsedLibraryItem
import javax.inject.Inject

class LibraryViewModel @Inject constructor(private val repository: LibraryRepository) : ViewModel() {
    val usedLibraryList  : LiveData<List<UsedLibraryItem>> = liveData {
        val data = repository.getAllUsedLibrary()
        emit(data)
    }

}