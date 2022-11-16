package com.parser.moviedb.data.di

import android.graphics.Bitmap
import com.parser.moviedb.data.remote.apis.ConfigurationApi
import com.parser.moviedb.data.remote.apis.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    fun provideConfigurationApi(retrofit: Retrofit): ConfigurationApi = retrofit.create(ConfigurationApi::class.java)
}