package com.parser.moviedb.domain.utils

import com.parser.moviedb.data.remote.models.MediaItemResponse
import com.parser.moviedb.data.remote.models.PagedItemsResponse
import com.parser.moviedb.domain.entities.MediaItem
import com.parser.moviedb.domain.entities.PagedItems

fun MediaItemResponse.toDomain(imageBaseUrl: String?) = MediaItem(
    id = id,
    posterUrl = "${imageBaseUrl}original/${posterPath}",
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

fun PagedItemsResponse.toDomain(imageBaseUrl: String?) = PagedItems(
    page = page,
    results = results.map { it.toDomain(imageBaseUrl) },
    totalResults = totalResults,
    totalPages = totalPages
)
