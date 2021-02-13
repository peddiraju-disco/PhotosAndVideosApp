package com.example.photosandvideosapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photosandvideosapp.data.local.PhotosDao
import com.example.photosandvideosapp.data.remote.response.ResultWrapper
import com.example.photosandvideosapp.data.repository.PhotosRepository
import com.example.photosandvideosapp.models.PhotoData
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val repository: PhotosRepository,
    private val photosDao: PhotosDao
) : ViewModel() {

    private val _photosData: MutableLiveData<List<PhotoData>> = MutableLiveData()
    val photosData: LiveData<List<PhotoData>>
        get() = _photosData

    private val _videosData: MutableLiveData<List<PhotoData>> = MutableLiveData()
    val videosData: LiveData<List<PhotoData>>
        get() = _videosData

    private val _banerData: MutableLiveData<List<PhotoData>> = MutableLiveData()
    val banersData: LiveData<List<PhotoData>>
        get() = _banerData

    fun getPhotos() {
        viewModelScope.launch {
            when (val resultResponse = repository.getPhotos()) {
                is ResultWrapper.Success -> {
                    val latestPhotos = resultResponse.data
                    _photosData.postValue(latestPhotos.photoData)
                }
                is ResultWrapper.Error -> {
                    Timber.e("Error in response ${resultResponse.exception.localizedMessage}")
                }
            }
        }
    }

    fun getVideos() {
        viewModelScope.launch {
            when (val resultResponse = repository.getVideos()) {
                is ResultWrapper.Success -> {
                    val latestPhotos = resultResponse.data
                    _videosData.postValue(latestPhotos.videosData)
                }
                is ResultWrapper.Error -> {
                    Timber.e("Error in response ${resultResponse.exception.localizedMessage}")
                }
            }
        }
    }

    fun getBanner() {
        viewModelScope.launch {
            when (val resultResponse = repository.getBannerPhoto()) {
                is ResultWrapper.Success -> {
                    val latestPhotos = resultResponse.data
                    _banerData.postValue(latestPhotos.photoData)
                }
                is ResultWrapper.Error -> {
                    Timber.e("Error in response ${resultResponse.exception.localizedMessage}")
                }
            }
        }
    }


    fun saveFavorite(photoData: PhotoData) {
        viewModelScope.launch {
            photosDao.insertItem(photoData)
        }
    }

    fun deleteFavorite(photoData: PhotoData) {
        viewModelScope.launch {
            photosDao.deleteItem(photoData)
        }
    }

}