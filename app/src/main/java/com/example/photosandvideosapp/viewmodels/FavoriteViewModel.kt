package com.example.photosandvideosapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photosandvideosapp.data.local.PhotosDao
import com.example.photosandvideosapp.models.PhotoData
import com.google.gson.Gson
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoriteViewModel(private val photosDao: PhotosDao) : ViewModel() {

    private val favoriteItems: MutableLiveData<List<PhotoData>> = MutableLiveData()
    val favoriteItemsLive: LiveData<List<PhotoData>>
        get() = favoriteItems

    init {
        loadFavorite()
    }

    fun loadFavorite() {
        viewModelScope.launch {
            val photosData = photosDao.getLocalItems()
            Timber.e("DATA FROM DB ${Gson().toJson(photosData)}")
            favoriteItems.postValue(photosData)
        }
    }

    fun deleteItem(photoData: PhotoData) {
        viewModelScope.launch {
            photosDao.deleteItem(photoData)
        }
    }
}