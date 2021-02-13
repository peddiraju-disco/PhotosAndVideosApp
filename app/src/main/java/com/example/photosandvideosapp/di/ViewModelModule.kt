package com.example.photosandvideosapp.di

import com.example.photosandvideosapp.viewmodels.FavoriteViewModel
import com.example.photosandvideosapp.viewmodels.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }
}