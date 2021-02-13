package com.example.photosandvideosapp.data.remote.response

import androidx.room.TypeConverters
import com.example.photosandvideosapp.models.VideoFileConverter
import com.google.gson.annotations.SerializedName

@TypeConverters(VideoFileConverter::class)
data class VideoFileData(
    @SerializedName("id")
    var id: String?,
    @SerializedName("quality")
    var quality: String?,
    @SerializedName("link")
    var link: String?
)