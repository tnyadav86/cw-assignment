package com.android.coolwinks.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.coolwinks.flickr.model.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * from Photo ORDER BY lastupdate DESC")
    fun getPhotos(): DataSource.Factory<Int, Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(photoList: List<Photo>)


    @Query("DELETE FROM Photo")
    fun deleteAll()
}