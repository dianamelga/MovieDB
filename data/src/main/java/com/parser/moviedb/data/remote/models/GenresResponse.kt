package com.parser.moviedb.data.remote.models

data class GenresResponse(
    val genres: List<GenreItemResponse>
)

data class GenreItemResponse(
    val id: Int,
    val name: String
)