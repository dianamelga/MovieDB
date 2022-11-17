package com.parser.moviedb.data.remote.datasources

import com.parser.moviedb.data.remote.apis.MovieGenresApi
import com.parser.moviedb.data.remote.datasources.interfaces.MovieGenresRemoteDataSource
import com.parser.moviedb.data.remote.models.GenresResponse

class MovieGenresDataSource(
    private val movieGenresApi: MovieGenresApi
) : MovieGenresRemoteDataSource {
    override suspend fun getMovieGenres(): Result<GenresResponse> {
        return try {
            val response = movieGenresApi.getMovieGenres()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.toString()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}