package com.parser.moviedb.domain.di

import com.parser.moviedb.data.repositories.interfaces.IApiConfigurationRepository
import com.parser.moviedb.data.repositories.interfaces.IMovieRepository
import com.parser.moviedb.domain.usecases.MovieUseCase
import com.parser.moviedb.domain.usecases.interfaces.IMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Singleton
    @Provides
    fun provideMovieUseCase(movieRepository: IMovieRepository, apiConfigRepository: IApiConfigurationRepository): IMovieUseCase = MovieUseCase(movieRepository, apiConfigRepository)
}
