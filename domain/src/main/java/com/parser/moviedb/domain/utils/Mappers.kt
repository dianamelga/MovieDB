package com.parser.moviedb.domain.utils

import com.parser.moviedb.data.remote.models.responses.MediaItemResponse
import com.parser.moviedb.data.remote.models.responses.PagedItemsResponse
import com.parser.moviedb.domain.entities.MediaItem
import com.parser.moviedb.domain.entities.PagedItems

fun MediaItemResponse.toDomain() = MediaItem(
    id = id,
    posterPath = posterPath,
    adult = adult,
    overview = overview,
    releaseDate = releaseDate,
    genreIds = genreIds,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    title = title,
    backdropPath = backdropPath,
    popularity = popularity,
    voteCount = voteCount,
    video = video,
    voteAverage = voteAverage
)

fun PagedItemsResponse.toDomain() = PagedItems(
    page = page,
    results = results.map { it.toDomain() },
    totalResults = totalResults,
    totalPages = totalPages
)
