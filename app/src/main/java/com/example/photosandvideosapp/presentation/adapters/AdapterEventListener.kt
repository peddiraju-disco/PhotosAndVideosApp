package com.example.photosandvideosapp.presentation.adapters

import com.example.photosandvideosapp.models.PhotoData

interface AdapterEventListener {
    fun onPhotoClickListener(photoData: PhotoData)
    fun onPhotoLikeListener(position: Int, artiphotocle: PhotoData)
    fun onPhotoUnfavoriteListener(position: Int, artiphotocle: PhotoData)
}
