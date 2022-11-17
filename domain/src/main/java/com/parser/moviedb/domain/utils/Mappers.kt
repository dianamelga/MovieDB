package com.parser.moviedb.domain.utils

import com.parser.moviedb.data.remote.models.GenreItemResponse
import com.parser.moviedb.data.remote.models.MediaItemResponse
import com.parser.moviedb.data.remote.models.MovieVideoResponse
import com.parser.moviedb.data.remote.models.PagedItemsResponse
import com.parser.moviedb.domain.entities.MediaItem
import com.parser.moviedb.domain.entities.MovieGenre
import com.parser.moviedb.domain.entities.MovieVideo
import com.parser.moviedb.domain.entities.PagedItems

fun MediaItemResponse.toDomain(imageBaseUrl: String?, genres: List<MovieGenre>) = MediaItem(
    id = id,
    posterUrl = "${imageBaseUrl}original/${posterPath}",
    adult = adult,
    overview = overview,
    releaseDate = releaseDate,
    genres = genres.filter { genreIds.contains(it.id) },
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    title = title,
    backdropPath = backdropPath,
    popularity = popularity,
    voteCount = voteCount,
    video = video,
    voteAverage = voteAverage
)

fun PagedItemsResponse.toDomain(imageBaseUrl: String?, genres: List<MovieGenre>) = PagedItems(
    page = page,
    results = results.map { it.toDomain(imageBaseUrl, genres) },
    totalResults = totalResults,
    totalPages = totalPages
)

fun GenreItemResponse.toDomain() = MovieGenre(
    id = id,
    name = name
)

fun MovieVideoResponse.toDomain(movieId: Int) = MovieVideo(
    movieId = movieId,
    language = language,
    name = name,
    key = key,
    site = site,
    type = type,
    official = official,
    publishedAt = publishedAt,
    id = id
)
