package com.example.photosandvideosapp.models

import androidx.room.TypeConverter
import com.example.photosandvideosapp.data.remote.response.VideoFileData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class VideoFileConverter {

    val gson = Gson()

    val type: Type = object : TypeToken<ArrayList<VideoFileData>?>() {}.type


    @TypeConverter
    fun fromSource(videoFileData: ArrayList<VideoFileData>?): String {
        return gson.toJson(videoFileData, type)
    }

    @TypeConverter
    fun toSource(json: String?): ArrayList<VideoFileData>? {
        return gson.fromJson(json, type)
    }
}