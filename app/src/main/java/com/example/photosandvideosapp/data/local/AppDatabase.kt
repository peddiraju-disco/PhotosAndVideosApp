package com.example.photosandvideosapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.photosandvideosapp.models.PhotoData
import com.example.photosandvideosapp.models.SourcesConverter
import com.example.photosandvideosapp.models.UserConverter
import com.example.photosandvideosapp.models.VideoFileConverter

@Database(entities = [PhotoData::class], version = 1, exportSchema = false)
@TypeConverters(SourcesConverter::class, UserConverter::class, VideoFileConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photosDao(): PhotosDao

}
