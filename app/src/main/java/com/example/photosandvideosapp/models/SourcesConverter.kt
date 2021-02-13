package com.example.photosandvideosapp.models

import androidx.room.TypeConverter
import com.example.photosandvideosapp.data.remote.response.SrcData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SourcesConverter {

    val gson = Gson()

    val type: Type = object : TypeToken<SrcData?>() {}.type


    @TypeConverter
    fun fromSource(srcData: SrcData?): String {
        return gson.toJson(srcData, type)
    }

    @TypeConverter
    fun toSource(json: String?): SrcData? {
        return gson.fromJson(json, type)
    }
}