package com.parser.moviedb.data.remote.datasources

import com.parser.moviedb.data.remote.apis.MovieApi
import com.parser.moviedb.data.remote.datasources.interfaces.MovieRemoteDataSource
import com.parser.moviedb.data.remote.models.PagedItemsResponse

class MovieDataSource(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {
    override suspend fun getUpcomingMovies(): Result<PagedItemsResponse> {
        return try {
            val response = movieApi.getUpcomingMovies()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.toString()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getTopRatedMovies(
        language: String,
        yearOfRelease: Int?
    ): Result<PagedItemsResponse> {
        return try {
            val response = movieApi.getTopRatedMovies(language = language)
            if (response.isSuccessful) {
                // TODO: filter by year of release
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.toString()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
