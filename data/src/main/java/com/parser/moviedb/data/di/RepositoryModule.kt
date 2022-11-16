package com.parser.moviedb.data.di

import com.parser.moviedb.data.remote.datasources.interfaces.MovieRemoteDataSource
import com.parser.moviedb.data.repositories.MovieRepository
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
}