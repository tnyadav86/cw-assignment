package com.android.coolwinks.network

import com.android.coolwings.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiClient {

    val client: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl("https://www.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(requestHeader)
                .build()
        }

    private val requestHeader: OkHttpClient
        private get() {
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.NONE
            }
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(RequestInterceptor())
                .addInterceptor(logging)
            return okHttpClient.build()
        }


    internal class RequestInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            return chain.proceed(requestBuilder.build())
        }
    }
}