package com.android.coolwinks.users.model

import androidx.lifecycle.MutableLiveData
import com.android.coolwinks.utils.DataResult
import com.android.coolwinks.utils.RepoResult
import com.android.coolwinks.utils.TaskStatusResult
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) {

    suspend fun getUsers(): RepoResult<List<Int>> {
        val networkErrors = MutableLiveData<TaskStatusResult>()
        networkErrors.postValue(TaskStatusResult.Loading(""))
        val savedData = userLocalDataSource.getAllUserIds()
        val response = remoteDataSource.getUserListFromAPI()
        when (response) {
            is DataResult.Success -> {
                userLocalDataSource.insertAllUser(response.data)
                networkErrors.postValue(TaskStatusResult.Success())
            }
            is DataResult.Error -> {
                networkErrors.postValue(TaskStatusResult.Error(response.errorMessage))
            }
        }

        return RepoResult(savedData, networkErrors)

    }

}