package com.parser.moviedb.data.remote.apis

import com.parser.moviedb.data.BuildConfig
import com.parser.moviedb.data.remote.models.GenresResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieGenresApi {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<GenresResponse>
}