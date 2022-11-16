package com.parser.moviedb.data.remote.datasources.interfaces

import com.parser.moviedb.data.remote.models.ApiConfigurationResponse

interface ApiConfigurationRemoteDataSource {
    suspend fun getApiConfiguration(): Result<ApiConfigurationResponse>
}
