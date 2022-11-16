package com.parser.moviedb.domain.usecases

import com.parser.moviedb.data.remote.apis.DEFAULT_LANGUAGE
import com.parser.moviedb.data.repositories.interfaces.IApiConfigurationRepository
import com.parser.moviedb.data.repositories.interfaces.IMovieRepository
import com.parser.moviedb.domain.entities.PagedItems
import com.parser.moviedb.domain.usecases.interfaces.IMovieUseCase
import com.parser.moviedb.domain.utils.toDomain

class MovieUseCase(
    private val movieRepository: IMovieRepository,
    private val apiConfigRepository: IApiConfigurationRepository
) : IMovieUseCase {
    private var imageBaseUrl: String? = null

    override suspend fun getUpcomingMovies(): Result<PagedItems> {
        setImageBaseUrl()

        val response = movieRepository.getUpcomingMovies().getOrElse { exception ->
            return Result.failure(exception)
        }.toDomain(imageBaseUrl)
        return Result.success(response)
    }

    override suspend fun getTopRatedMovies(): Result<PagedItems> {
        setImageBaseUrl()

        val response = movieRepository.getTopRatedMovies(language = DEFAULT_LANGUAGE).getOrElse { exception ->
            return Result.failure(exception)
        }.toDomain(imageBaseUrl)
        return Result.success(response)
    }

    override suspend fun getRecommendedMovies(language: String?, yearOfRelease: Int?): Result<PagedItems> {
        setImageBaseUrl()

        val response = movieRepository.getTopRatedMovies(language = language ?: DEFAULT_LANGUAGE, yearOfRelease = yearOfRelease).getOrElse { exception ->
            return Result.failure(exception)
        }.toDomain(imageBaseUrl)
        return Result.success(response)
    }

    private suspend fun setImageBaseUrl() {
        imageBaseUrl = imageBaseUrl ?: apiConfigRepository.getApiConfiguration().getOrNull()?.images?.baseUrl
    }
}
