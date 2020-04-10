package com.android.coolwinks.libraries.model

import android.content.res.AssetManager
import com.google.gson.Gson
import javax.inject.Inject

class LibraryRepository @Inject constructor(private val assetManager: AssetManager) {
    companion object {
        const val LIBRARY_JSON = "library.json"
    }

    suspend fun getAllUsedLibrary(): List<UsedLibraryItem> {
        var usedLibrary = ArrayList<UsedLibraryItem>()
        try {
            val jsonString = assetManager.open(LIBRARY_JSON).bufferedReader().use {
                it.readText()
            }
            usedLibrary = Gson().fromJson(jsonString, UsedLibrary::class.java)
        } catch (e: Exception) {
        }
        return usedLibrary

    }

}