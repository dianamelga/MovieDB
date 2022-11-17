package com.parser.moviedb.data.di

import com.parser.moviedb.data.remote.datasources.interfaces.ApiConfigurationRemoteDataSource
import com.parser.moviedb.data.remote.datasources.interfaces.MovieGenresRemoteDataSource
import com.parser.moviedb.data.remote.datasources.interfaces.MovieRemoteDataSource
import com.parser.moviedb.data.repositories.ApiConfigurationRepository
import com.parser.moviedb.data.repositories.MovieGenresRepository
import com.parser.moviedb.data.repositories.MovieRepository
import com.parser.moviedb.data.repositories.interfaces.IApiConfigurationRepository
import com.parser.moviedb.data.repositories.interfaces.IMovieGenresRepository
import com.parser.moviedb.data.repositories.interfaces.IMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(dataSource: MovieRemoteDataSource): IMovieRepository = MovieRepository(dataSource)

    @Singleton
    @Provides
    fun provideApiConfigurationRepository(dataSource: ApiConfigurationRemoteDataSource): IApiConfigurationRepository = ApiConfigurationRepository(dataSource)

    @Singleton
    @Provides
    fun provideMovieGenresRepository(dataSource: MovieGenresRemoteDataSource): IMovieGenresRepository = MovieGenresRepository(dataSource)
}