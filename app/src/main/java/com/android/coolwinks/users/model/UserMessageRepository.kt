package com.android.coolwinks.users.model

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UserMessageRepository @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {

    fun getUsersByUserId(userId: Int) = liveData(Dispatchers.IO) {
        emitSource(userLocalDataSource.getAllUserById(userId))
    }

}