package com.android.coolwinks.users.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
) {
    @Ignore
    var isExpended = false
}