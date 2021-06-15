package com.example.fmovies.di

import com.example.fmovies.api.ApiService
import com.example.fmovies.helper.Constant
import com.example.fmovies.helper.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    fun provideBaseURL() = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseURL: String): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}

