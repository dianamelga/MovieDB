package com.parser.moviedb.data.remote.apis

import com.parser.moviedb.data.remote.models.PagedItemsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String? = null
    ): Response<PagedItemsResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<PagedItemsResponse>
}
