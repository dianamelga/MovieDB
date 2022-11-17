package com.parser.moviedb.data.remote.datasources.interfaces

import com.parser.moviedb.data.remote.models.GenresResponse

interface MovieGenresRemoteDataSource {
    suspend fun getMovieGenres(): Result<GenresResponse>
}