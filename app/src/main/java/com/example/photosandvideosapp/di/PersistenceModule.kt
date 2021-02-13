package com.example.photosandvideosapp.di

import android.content.Context
import androidx.room.Room
import com.example.photosandvideosapp.R
import com.example.photosandvideosapp.data.local.AppDatabase
import com.example.photosandvideosapp.data.local.PhotosDao
import org.koin.dsl.module

object PersistenceModule {
    val persistenceModule = module {
        single { provideAppDatabase(get()) }
        single { providePhotossDao(get()) }
    }

    private fun providePhotossDao(appDatabase: AppDatabase): PhotosDao {
        return appDatabase.photosDao()
    }

    private fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java,
            context.applicationContext.getString(R.string.app_name)
        ).build()
    }
}




