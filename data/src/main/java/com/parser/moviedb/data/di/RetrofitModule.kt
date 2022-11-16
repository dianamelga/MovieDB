package com.parser.moviedb.data.di

import android.content.Context
import androidx.multidex.BuildConfig
import com.parser.moviedb.data.remote.apis.CACHE_SIZE
import com.parser.moviedb.data.remote.apis.TIMEOUT_SECONDS
import com.parser.moviedb.data.remote.interceptors.ApiInterceptor
import com.parser.moviedb.data.remote.interceptors.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(@ApplicationContext context: Context) = "https://api.themoviedb.org/3/"

    @Provides
    fun provideRetrofit(@Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: LoggingInterceptor,
        apiInterceptor: ApiInterceptor,
        cache: Cache
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(apiInterceptor)

        if (BuildConfig.DEBUG)
            clientBuilder.addInterceptor(httpLoggingInterceptor)

        return clientBuilder.build()
    }

    @Provides
    fun provideApiInterceptor(@ApplicationContext context: Context): ApiInterceptor = ApiInterceptor(context)

    @Provides
    fun provideHttpLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor()
    }

    @Provides
    fun providerCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }

}