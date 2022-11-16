package com.parser.moviedb.data.remote.apis

import com.parser.moviedb.data.remote.models.ApiConfigurationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfigurationApi {

    @GET("configuration")
    suspend fun getApiConfiguration(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<ApiConfigurationResponse>
}
