package com.android.coolwinks.users.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
) {
    override fun toString(): String {
        return "User(id=$id, title='$title', userId=$userId)"
    }
}