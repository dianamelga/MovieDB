package com.parser.moviedb.data.repositories

import com.parser.moviedb.data.remote.datasources.interfaces.MovieRemoteDataSource
import com.parser.moviedb.data.remote.models.PagedItemsResponse
import com.parser.moviedb.data.repositories.interfaces.IMovieRepository

class MovieRepository(
    private val dataSource: MovieRemoteDataSource
) : IMovieRepository {
    override suspend fun getUpcomingMovies(): Result<PagedItemsResponse> {
        return dataSource.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(
        language: String,
        yearOfRelease: Int?
    ): Result<PagedItemsResponse> {
        return dataSource.getTopRatedMovies(language, yearOfRelease)
    }
}
