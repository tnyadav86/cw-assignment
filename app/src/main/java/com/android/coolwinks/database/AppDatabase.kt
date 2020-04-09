package com.android.coolwinks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.coolwinks.database.dao.PhotoDao
import com.android.coolwinks.database.dao.UserDao
import com.android.coolwinks.flickr.model.Photo
import com.android.coolwinks.users.model.User


@Database(
    entities = arrayOf(
        User::class,
        Photo::class
    ), version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun photoDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val DATABASE_NAME = "CoolWinksDatabase"
        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}