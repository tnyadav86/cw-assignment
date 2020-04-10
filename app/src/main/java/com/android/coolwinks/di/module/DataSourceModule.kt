package com.android.coolwinks.di.module

import android.app.Application
import android.content.res.AssetManager
import com.android.coolwinks.database.AppDatabase
import com.android.coolwinks.database.dao.PhotoDao
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

    @Provides
    fun providePhotoDao(application: Application): PhotoDao {
        return AppDatabase.getInstance(application.applicationContext).photoDao()
    }

    @Provides
    fun provideAssetManager(application: Application): AssetManager{
        return  application.assets

    }

}