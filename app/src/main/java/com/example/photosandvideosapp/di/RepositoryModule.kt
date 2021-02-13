package com.example.photosandvideosapp.di

import com.example.photosandvideosapp.data.repository.PhotosRepository
import org.koin.dsl.module


val repositoryModule = module {
    single { PhotosRepository(get()) }

}