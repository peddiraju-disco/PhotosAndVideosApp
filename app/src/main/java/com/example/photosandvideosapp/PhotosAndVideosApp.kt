package com.example.photosandvideosapp

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.example.photosandvideosapp.di.NetModule
import com.example.photosandvideosapp.di.PersistenceModule
import com.example.photosandvideosapp.di.repositoryModule
import com.example.photosandvideosapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class PhotosAndVideosApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initKoinDi()
    }

    private fun initKoinDi() {
        startKoin {
            androidContext(this@PhotosAndVideosApp)
            modules(NetModule.networkModule)
            modules(PersistenceModule.persistenceModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}