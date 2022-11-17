package com.parser.moviedb.data.remote.apis

import com.parser.moviedb.data.remote.models.GenresResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieGenresApi {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): Response<GenresResponse>
}