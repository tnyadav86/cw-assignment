package com.android.coolwinks.users.model

import androidx.lifecycle.LiveData
import com.android.coolwinks.database.dao.UserDao
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val userDao: UserDao) {

    fun insertAllUser(users: List<User>) {
        userDao.insertAll(users)
    }

    suspend fun getAllUserIds(): LiveData<List<Int>> {
        return userDao.getAllUsersIDs()
    }
}