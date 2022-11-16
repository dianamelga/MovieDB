package com.parser.moviedb.domain.usecases

import com.parser.moviedb.data.remote.apis.DEFAULT_LANGUAGE
import com.parser.moviedb.data.repositories.interfaces.IMovieRepository
import com.parser.moviedb.domain.entities.PagedItems
import com.parser.moviedb.domain.usecases.interfaces.IMovieUseCase
import com.parser.moviedb.domain.utils.toDomain

class MovieUseCase(
    private val movieRepository: IMovieRepository
) : IMovieUseCase {
    override suspend fun getUpcomingMovies(): Result<PagedItems> {
        val response = movieRepository.getUpcomingMovies().getOrElse { exception ->
            return Result.failure(exception)
        }.toDomain()
        return Result.success(response)
    }

    override suspend fun getTopRatedMovies(): Result<PagedItems> {
        val response = movieRepository.getTopRatedMovies(language = DEFAULT_LANGUAGE).getOrElse { exception ->
            return Result.failure(exception)
        }.toDomain()
        return Result.success(response)
    }

    override suspend fun getRecommendedMovies(language: String?, yearOfRelease: Int?): Result<PagedItems> {
        val response = movieRepository.getTopRatedMovies(language = DEFAULT_LANGUAGE, yearOfRelease = yearOfRelease).getOrElse { exception ->
            return Result.failure(exception)
        }.toDomain()
        return Result.success(response)
    }
}
