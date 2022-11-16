package com.parser.moviedb.domain.entities

data class MediaItem(
    val id: Int,
    val posterPath: String? = null,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    val genreIds: List<Int>,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String? = null,
    val popularity: Float,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Float
)
