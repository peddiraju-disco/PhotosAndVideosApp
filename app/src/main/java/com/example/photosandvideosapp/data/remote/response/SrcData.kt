package com.example.photosandvideosapp.data.remote.response

import androidx.room.TypeConverters
import com.example.photosandvideosapp.models.SourcesConverter
import com.google.gson.annotations.SerializedName

@TypeConverters(SourcesConverter::class)
data class SrcData(
    @SerializedName("original")
    var original: String?,
    @SerializedName("large2x")
    var large2x: String?,
    @SerializedName("large")
    var large: String?,
    @SerializedName("medium")
    var medium: String?,
    @SerializedName("small")
    var small: String?,
    @SerializedName("portrait")
    var portrait: String?,
    @SerializedName("landscape")
    var landscape: String?,
    @SerializedName("tiny")
    var tiny: String?
)