package com.android.coolwinks.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.coolwinks.users.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getUserComment(): List<User>

    @Query("SELECT DISTINCT userId from User ORDER BY userId ASC")
    fun getAllUsersIDs(): LiveData<List<Int>>

    @Query("SELECT * from User WHERE userId = :userId")
    fun getAllUsersByID(userId : Int): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stateList: List<User>)
}