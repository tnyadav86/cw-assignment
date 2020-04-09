package com.android.coolwinks.network

import com.android.coolwinks.flickr.model.FlickrDataResponse
import com.android.coolwinks.users.model.UserDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET
    fun getDataFromUserApi(@Url url: String = "http://jsonplaceholder.typicode.com/posts/"): Call<UserDataResponse>

    @GET("services/rest/")
    fun getDataFromFlickrApi(
        @Query("method") method: String = "flickr.photos.getRecent",
        @Query("api_key") api_key: String = "e449b259146e14b0d55e770fb3577436",
        @Query("extras") extras: String = "date_upload,last_update,media,url_q,url_z",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: String = "1",
        @Query("page") page: String, @Query("per_page") per_page: String
    ): Call<FlickrDataResponse>

}