package com.android.coolwinks.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class DataResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val errorMessage: String) : DataResult<Nothing>()

}
