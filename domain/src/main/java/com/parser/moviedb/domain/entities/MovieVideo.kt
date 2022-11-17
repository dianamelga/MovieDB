package com.parser.moviedb.domain.entities

data class MovieVideo(
    val movieId: Int,
    val language: String,
    val name: String,
    val key: String,
    val site: String,
    val type: String,
    val official: Boolean,
    val publishedAt: String,
    val id: String
)
