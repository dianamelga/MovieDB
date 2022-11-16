package com.parser.moviedb.data.repositories.interfaces

import com.parser.moviedb.data.remote.models.ApiConfigurationResponse

interface IApiConfigurationRepository {

    suspend fun getApiConfiguration(): Result<ApiConfigurationResponse>
}