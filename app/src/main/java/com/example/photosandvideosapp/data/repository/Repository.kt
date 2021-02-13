package com.example.photosandvideosapp.data.repository

import com.example.photosandvideosapp.data.remote.response.PhotosResponse
import com.example.photosandvideosapp.data.remote.response.ResultWrapper

interface Repository {

    suspend fun getPhotos(): ResultWrapper<PhotosResponse>

    suspend fun getVideos(): ResultWrapper<PhotosResponse>

    suspend fun getBannerPhoto(): ResultWrapper<PhotosResponse>


}