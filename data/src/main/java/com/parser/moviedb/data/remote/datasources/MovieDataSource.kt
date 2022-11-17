package com.parser.moviedb.data.remote.datasources

import com.parser.moviedb.data.remote.apis.MovieApi
import com.parser.moviedb.data.remote.datasources.interfaces.MovieRemoteDataSource
import com.parser.moviedb.data.remote.models.MovieVideosResponse
import com.parser.moviedb.data.remote.models.PagedItemsResponse
import com.parser.moviedb.data.utils.toISO8601Date
import java.util.Calendar

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
                val data = response.body()!!
                val moviesFiltered = if (yearOfRelease != null) {
                    data.results.filter {
                        val calendar: Calendar? = it.releaseDate.toISO8601Date()?.let { date ->
                            Calendar.getInstance().also { c ->
                                c.time = date
                            }
                        } ?: run { null }

                        (calendar?.get(Calendar.YEAR) ?: 0) == yearOfRelease
                    }
                } else {
                    data.results
                }
                data.results = moviesFiltered
                Result.success(data)
            } else {
                Result.failure(Exception(response.errorBody()?.toString()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVideosFromMovie(movieId: Int): Result<MovieVideosResponse> {
        return try {
            val response = movieApi.getVideosFromMovie(movieId)
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
