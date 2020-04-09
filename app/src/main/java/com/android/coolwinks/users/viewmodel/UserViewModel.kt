package com.android.coolwinks.users.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.android.coolwinks.users.model.UserRepository
import com.android.coolwinks.utils.RepoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(val repository: UserRepository) : ViewModel() {

    private val userRepoResult = MutableLiveData<RepoResult<List<Int>>>()

    val userIdListLivedata = userRepoResult.switchMap {
        it.data
    }
    val taskStatusLiveData = userRepoResult.switchMap {
        it.networkErrors
    }

    fun getUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getUsers()
            userRepoResult.postValue(data)
        }

    }
}