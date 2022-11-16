package com.parser.moviedb.domain.entities

data class PagedItems(
    val page: Int,
    val results: List<MediaItem>,
    val totalResults: Int,
    val totalPages: Int
)
