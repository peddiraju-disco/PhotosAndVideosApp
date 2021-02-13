package com.example.photosandvideosapp.data.local

import androidx.room.*
import com.example.photosandvideosapp.models.PhotoData

@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(photoData: PhotoData)

    @Query("SELECT * from `photoData`")
    suspend fun getLocalItems(): List<PhotoData>

    @Delete
    suspend fun deleteItem(photoData: PhotoData)
}