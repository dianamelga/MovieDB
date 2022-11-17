package com.parser.moviedb.data.remote.models

import com.google.gson.annotations.SerializedName

data class MovieVideosResponse(
    val id: Int,
    val results: List<MovieVideoResponse>
)

data class MovieVideoResponse(
    @SerializedName("iso_639_1")
    val language: String,
    val name: String,
    val key: String,
    val site: String,
    val type: String,
    val official: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    val id: String
)