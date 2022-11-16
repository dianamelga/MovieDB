package com.parser.moviedb.data.di

import com.parser.moviedb.data.remote.apis.MovieApi
import com.parser.moviedb.data.remote.datasources.MovieDataSource
import com.parser.moviedb.data.remote.datasources.interfaces.MovieRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(movieApi: MovieApi): MovieRemoteDataSource = MovieDataSource(movieApi)
}