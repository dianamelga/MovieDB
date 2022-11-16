package com.parser.moviedb.data.repositories

import com.parser.moviedb.data.remote.datasources.interfaces.ApiConfigurationRemoteDataSource
import com.parser.moviedb.data.remote.models.ApiConfigurationResponse
import com.parser.moviedb.data.repositories.interfaces.IApiConfigurationRepository

class ApiConfigurationRepository(
    private val dataSource: ApiConfigurationRemoteDataSource
) : IApiConfigurationRepository {
    override suspend fun getApiConfiguration(): Result<ApiConfigurationResponse> {
        return dataSource.getApiConfiguration()
    }
}