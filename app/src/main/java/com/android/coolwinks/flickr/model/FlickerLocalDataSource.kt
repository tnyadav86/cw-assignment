package com.android.coolwinks.flickr.model

import androidx.paging.DataSource
import com.android.coolwinks.database.dao.PhotoDao

class FlickerLocalDataSource(private val photoDao: PhotoDao) {

    fun insertPhoto(photoList: List<Photo>, insertFinished: () -> Unit) {
        photoDao.insertAll(photoList)
        insertFinished()
    }

    fun deletePhoto(deleteFinished: () -> Unit) {
        photoDao.deleteAll()
        deleteFinished()
    }

    fun getPhotos(): DataSource.Factory<Int, Photo> {
        return photoDao.getPhotos()
    }
}