package com.parser.moviedb.data.remote.models

import com.google.gson.annotations.SerializedName

data class ApiConfigurationResponse(
    val images: ImageConfigurationResponse
)

data class ImageConfigurationResponse(
    @SerializedName("base_url")
    val baseUrl: String,
    @SerializedName("secure_base_url")
    val secureBaseUrl: String
)