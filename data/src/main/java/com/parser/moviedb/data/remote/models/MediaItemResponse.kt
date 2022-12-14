package com.parser.moviedb.data.remote.models

import com.google.gson.annotations.SerializedName

data class MediaItemResponse(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val popularity: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Float
)
