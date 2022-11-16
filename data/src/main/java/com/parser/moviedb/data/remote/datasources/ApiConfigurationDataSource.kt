package com.parser.moviedb.data.remote.datasources

import com.parser.moviedb.data.remote.apis.ConfigurationApi
import com.parser.moviedb.data.remote.datasources.interfaces.ApiConfigurationRemoteDataSource
import com.parser.moviedb.data.remote.models.ApiConfigurationResponse

class ApiConfigurationDataSource(
    private val configurationApi: ConfigurationApi
): ApiConfigurationRemoteDataSource {
    override suspend fun getApiConfiguration(): Result<ApiConfigurationResponse> {
        return try {
            val response = configurationApi.getApiConfiguration()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.toString()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
