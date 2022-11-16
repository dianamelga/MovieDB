package com.parser.moviedb.domain.usecases.interfaces
import com.parser.moviedb.domain.entities.PagedItems

interface IMovieUseCase {

    suspend fun getUpcomingMovies(): Result<PagedItems>

    suspend fun getTopRatedMovies(): Result<PagedItems>

    suspend fun getRecommendedMovies(language: String? = null, yearOfRelease: Int? = null): Result<PagedItems>

}