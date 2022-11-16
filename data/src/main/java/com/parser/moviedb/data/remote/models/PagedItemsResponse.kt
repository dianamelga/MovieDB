package com.parser.moviedb.data.remote.models

import com.google.gson.annotations.SerializedName

data class PagedItemsResponse(
    val page: Int,
    val results: List<MediaItemResponse>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
