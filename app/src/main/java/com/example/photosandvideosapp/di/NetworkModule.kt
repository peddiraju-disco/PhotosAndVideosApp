package com.example.photosandvideosapp.di

import android.content.Context
import com.example.photosandvideosapp.BuildConfig
import com.example.photosandvideosapp.data.remote.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetModule {

    val networkModule = module {
        //GENERATE CACHE SINGLETON
        single { provideCache(get()) }

        //GENERATE OKHTTP CLIENT SINGLETON
        single { provideOkHttpClient(get()) }

        //GENERATE RETROFIT CLIENT SINGLETON
        single { provideRetrofit(get()) }

        //GENERATE API SERVICE SINGLETON
        single { provideAppService(get()) }
    }


    private fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .header("X-API-Key", BuildConfig.API_KEY)
                chain.proceed(newRequest.build())
            }
            .addInterceptor(logger)
            .build()
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
    }

    private fun provideCache(context: Context): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize)
    }


    private fun provideAppService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}