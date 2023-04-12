package com.example.mobilecodingchallenge.images

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

data class Image(
    val id: String,
    val title: String,
    val urls: Urls
)


data class Urls(
    val regular: String
)

interface UnsplashApi {
    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") clientId: String,
        @Query("per_page") perPage: Int = 20
    ): Response<List<Image>>
}
