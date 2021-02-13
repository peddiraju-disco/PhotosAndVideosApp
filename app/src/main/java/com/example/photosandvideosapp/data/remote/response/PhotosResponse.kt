package com.example.photosandvideosapp.data.remote.response

import com.example.photosandvideosapp.models.PhotoData
import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    @SerializedName("photos")
    var photoData: List<PhotoData>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("totalResults")
    var totalResults: Int?,
    @SerializedName("videos")
    var videosData: List<PhotoData>?
)