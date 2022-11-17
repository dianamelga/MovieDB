package com.parser.moviedb.data.remote.datasources.interfaces

import com.parser.moviedb.data.remote.models.MovieVideosResponse
import com.parser.moviedb.data.remote.models.PagedItemsResponse

interface MovieRemoteDataSource {

    suspend fun getUpcomingMovies(): Result<PagedItemsResponse>

    suspend fun getTopRatedMovies(language: String, yearOfRelease: Int? = null): Result<PagedItemsResponse>

    suspend fun getVideosFromMovie(movieId: Int): Result<MovieVideosResponse>
}
