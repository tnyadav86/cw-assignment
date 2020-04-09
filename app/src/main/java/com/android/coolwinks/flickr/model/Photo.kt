package com.android.coolwinks.flickr.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @PrimaryKey
    val id: String,
    val dateupload: String,
    val lastupdate: String,
    val media_status: String?,
    val url_q: String?,
    val url_z: String?
)