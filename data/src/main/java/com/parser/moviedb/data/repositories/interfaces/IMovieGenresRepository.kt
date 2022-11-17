package com.parser.moviedb.data.repositories.interfaces

import com.parser.moviedb.data.remote.models.GenresResponse

interface IMovieGenresRepository {
    suspend fun getMovieGenres(): Result<GenresResponse>
}