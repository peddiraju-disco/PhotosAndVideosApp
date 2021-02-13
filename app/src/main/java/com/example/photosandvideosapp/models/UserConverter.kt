package com.example.photosandvideosapp.models

import androidx.room.TypeConverter
import com.example.photosandvideosapp.data.remote.response.UserData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class UserConverter {

    val gson = Gson()

    val type: Type = object : TypeToken<UserData?>() {}.type


    @TypeConverter
    fun fromSource(userData: UserData?): String {
        return gson.toJson(userData, type)
    }

    @TypeConverter
    fun toSource(json: String?): UserData? {
        return gson.fromJson(json, type)
    }
}