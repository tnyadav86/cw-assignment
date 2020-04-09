package com.android.coolwinks.utils

import androidx.lifecycle.LiveData

data class RepoResult< T : Any>(
    val data: LiveData<T>,
    val networkErrors: LiveData<TaskStatusResult>
)