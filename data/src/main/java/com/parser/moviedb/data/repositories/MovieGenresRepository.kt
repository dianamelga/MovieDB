package com.parser.moviedb.data.repositories

import com.parser.moviedb.data.remote.datasources.interfaces.MovieGenresRemoteDataSource
import com.parser.moviedb.data.remote.models.GenresResponse
import com.parser.moviedb.data.repositories.interfaces.IMovieGenresRepository

class MovieGenresRepository(
    private val dataSource: MovieGenresRemoteDataSource
) : IMovieGenresRepository {
    override suspend fun getMovieGenres(): Result<GenresResponse> {
        return dataSource.getMovieGenres()
    }
}