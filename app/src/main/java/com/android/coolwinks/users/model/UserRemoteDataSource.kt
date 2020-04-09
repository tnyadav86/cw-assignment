package com.android.coolwinks.users.model

import com.android.coolwinks.network.ApiService
import com.android.coolwinks.utils.DataResult
import okio.IOException
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val service: ApiService) {

    suspend fun getUserListFromAPI(): DataResult<List<User>> {

        try {
            val response = service.getDataFromUserApi().execute()
            return if (response.isSuccessful) {
                val userResponseData = response.body()
                if (userResponseData.isNullOrEmpty()) {
                    return DataResult.Error("User list is empty")
                } else {
                    return DataResult.Success(userResponseData as List<User>)
                }

            } else {
                return DataResult.Error(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            if (e is IOException) {
                return DataResult.Error("Please check your network connection")
            } else {
                return DataResult.Error("Unknown error")
            }

        }

    }
}