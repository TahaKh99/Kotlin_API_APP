package com.example.mobilecodingchallenge.images

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class UnsplashClient(private val accessKey: String) {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // Increase to 30 seconds
        .readTimeout(30, TimeUnit.SECONDS) // Increase to 30 seconds
        .build()
    private val unsplashApi: UnsplashApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        unsplashApi = retrofit.create(UnsplashApi::class.java)
    }

    suspend fun getPhotos(): Response<List<Image>> {
        return unsplashApi.getPhotos(accessKey)
    }
}
