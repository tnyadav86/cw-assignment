package com.android.coolwinks.flickr.model

import com.android.coolwinks.network.ApiService
import okio.IOException
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class FlickrRemoteDataSource @Inject constructor(private val service: ApiService,private val apiKey:String) {
    fun getPhotoFromFlickrAPI(
        page: String, perPage: String,
        onSuccess: (photos: Photos) -> Unit,
        onError: (error: String) -> Unit
    ) {
        val call = service.getDataFromFlickrApi(page = page, per_page = perPage,api_key = apiKey)
        call.enqueue(object : retrofit2.Callback<FlickrDataResponse> {
            override fun onFailure(call: Call<FlickrDataResponse>, t: Throwable) {
                if (t is IOException) {
                    onError("Network failure. Please try again later")
                    // logging probably not necessary
                } else {
                    onError("Unknown error")
                }
            }

            override fun onResponse(
                call: Call<FlickrDataResponse>,
                response: Response<FlickrDataResponse>
            ) {
                if (response.isSuccessful) {
                    val userDataResponse = response.body()
                    userDataResponse?.let {
                        onSuccess(it.photos)
                    } ?: onError("Unable to get Photo list")
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }

        })

    }
}