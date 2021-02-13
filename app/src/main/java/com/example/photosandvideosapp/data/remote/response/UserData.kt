package com.example.photosandvideosapp.data.remote.response

import androidx.room.TypeConverters
import com.example.photosandvideosapp.models.UserConverter
import com.google.gson.annotations.SerializedName

@TypeConverters(UserConverter::class)
data class UserData(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("url")
    var url: String?
)