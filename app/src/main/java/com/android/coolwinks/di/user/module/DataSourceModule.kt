package com.android.coolwinks.di.user.module

import android.app.Application
import com.android.coolwinks.database.AppDatabase
import com.android.coolwinks.database.dao.UserDao
import com.android.coolwinks.network.ApiClient
import com.android.coolwinks.network.ApiService
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {
    @Provides
    fun provideApiService(): ApiService {
        return ApiClient.client.create(ApiService::class.java)
    }

    @Provides
    fun provideUserDao(application: Application): UserDao {
        return AppDatabase.getInstance(application.applicationContext).userDao()
    }

}