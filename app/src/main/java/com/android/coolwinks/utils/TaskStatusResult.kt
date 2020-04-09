package com.android.coolwinks.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class TaskStatusResult{

    data class Success(val message: String?=null) : TaskStatusResult()
    data class Error(val errorMessage: String?=null) : TaskStatusResult()
    data class Loading(val message: String?=null) : TaskStatusResult()

}
