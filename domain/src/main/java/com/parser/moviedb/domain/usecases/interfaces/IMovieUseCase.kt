package com.parser.moviedb.domain.usecases.interfaces
import com.parser.moviedb.domain.entities.MovieVideo
import com.parser.moviedb.domain.entities.PagedItems

interface IMovieUseCase {

    suspend fun getUpcomingMovies(): Result<PagedItems>

    suspend fun getTopRatedMovies(): Result<PagedItems>

    suspend fun getRecommendedMovies(language: String? = null, yearOfRelease: Int? = null): Result<PagedItems>

    suspend fun getMovieVideos(movieId: Int, language: String? = null): Result<List<MovieVideo>>
}