package com.example.photosandvideosapp.data.repository

import com.example.photosandvideosapp.data.remote.ApiService
import com.example.photosandvideosapp.data.remote.response.PhotosResponse
import com.example.photosandvideosapp.data.remote.response.ResultWrapper
import com.google.gson.Gson
import timber.log.Timber

class PhotosRepository(private val apiService: ApiService) : Repository {

    override suspend fun getPhotos(): ResultWrapper<PhotosResponse> {
        try {
            val dataResponse = apiService.getPhotosData()
            var photosResponse: PhotosResponse? = null
            when {
                dataResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(dataResponse.body())}")
                    photosResponse = dataResponse.body()!!
                }
                else -> {
                    Timber.e("ERROR RESP ${Gson().toJson(dataResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(photosResponse!!)

        } catch (exception: Exception) {

            val errorResponse = ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }

    override suspend fun getVideos(): ResultWrapper<PhotosResponse> {
        try {
            val dataResponse = apiService.getVideosData()
            var photosResponse: PhotosResponse? = null
            when {
                dataResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(dataResponse.body())}")
                    photosResponse = dataResponse.body()!!
                }
                else -> {
                    Timber.e("ERROR RESP ${Gson().toJson(dataResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(photosResponse!!)

        } catch (exception: Exception) {

            val errorResponse = ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }

    override suspend fun getBannerPhoto(): ResultWrapper<PhotosResponse> {
        try {
            val dataResponse = apiService.getBannerPhoto()
            var photosResponse: PhotosResponse? = null
            when {
                dataResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(dataResponse.body())}")
                    photosResponse = dataResponse.body()!!
                }
                else -> {
                    Timber.e("ERROR RESP ${Gson().toJson(dataResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(photosResponse!!)

        } catch (exception: Exception) {

            val errorResponse = ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }

}