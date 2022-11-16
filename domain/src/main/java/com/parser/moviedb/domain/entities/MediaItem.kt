package com.parser.moviedb.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaItem(
    val id: Int,
    val posterUrl: String? = null,
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
) : Parcelable {
    companion object {
        fun dummy() = MediaItem(
            id = 0,
            posterUrl = "",
            adult = false,
            overview = "",
            releaseDate = "",
            genreIds = emptyList(),
            originalTitle = "",
            originalLanguage = "",
            title = "",
            backdropPath = "",
            popularity = 0f,
            voteCount = 0,
            video = false,
            voteAverage = 0f
        )
    }
}
