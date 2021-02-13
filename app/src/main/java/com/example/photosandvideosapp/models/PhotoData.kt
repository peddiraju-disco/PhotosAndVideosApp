package com.example.photosandvideosapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.photosandvideosapp.data.remote.response.SrcData
import com.example.photosandvideosapp.data.remote.response.UserData
import com.example.photosandvideosapp.data.remote.response.VideoFileData
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photoData")
data class PhotoData(

    @PrimaryKey @SerializedName("id")
    var id: Int?,

    @SerializedName("url")
    var url: String?,

    @SerializedName("photographer")
    var photographer: String?,

    @SerializedName("src")
    var srcdata: SrcData?,

    @SerializedName("user")
    var user: UserData?,

    @SerializedName("video_files")
    var video_files: ArrayList<VideoFileData>?,

    var isBookmark: Boolean = false,

    var isVideo: Boolean = false,

    @SerializedName("image")
    var image: String?
)