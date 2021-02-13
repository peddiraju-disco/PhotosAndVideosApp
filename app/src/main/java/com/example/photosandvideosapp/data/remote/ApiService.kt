package com.example.photosandvideosapp.data.remote

import com.example.photosandvideosapp.BuildConfig
import com.example.photosandvideosapp.constant.pageNo
import com.example.photosandvideosapp.constant.perPage_1
import com.example.photosandvideosapp.constant.perPage_20
import com.example.photosandvideosapp.constant.queryValue
import com.example.photosandvideosapp.data.remote.response.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("v1/search?")
    suspend fun getPhotosData(
        @Query("per_page") per_page: Int = perPage_20,
        @Query("page") page: Int = pageNo,
        @Query("query") query: String = queryValue,
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY
    ): Response<PhotosResponse>

    @GET("/videos/popular?")
    suspend fun getVideosData(
        @Query("per_page") per_page: Int = perPage_20,
        @Query("page") page: Int = pageNo,
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY
    ): Response<PhotosResponse>

    @GET("v1/curated?")
    suspend fun getBannerPhoto(
        @Query("per_page") per_page: Int = perPage_1,
        @Query("page") page: Int = pageNo,
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY
    ): Response<PhotosResponse>
}
