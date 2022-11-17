package com.parser.moviedb.domain.usecases

import com.parser.moviedb.data.remote.apis.DEFAULT_LANGUAGE
import com.parser.moviedb.data.repositories.interfaces.IApiConfigurationRepository
import com.parser.moviedb.data.repositories.interfaces.IMovieGenresRepository
import com.parser.moviedb.data.repositories.interfaces.IMovieRepository
import com.parser.moviedb.domain.entities.MovieGenre
import com.parser.moviedb.domain.entities.MovieVideo
import com.parser.moviedb.domain.entities.PagedItems
import com.parser.moviedb.domain.usecases.interfaces.IMovieUseCase
import com.parser.moviedb.domain.utils.toDomain

class MovieUseCase(
    private val movieRepository: IMovieRepository,
    private val apiConfigRepository: IApiConfigurationRepository,
    private val movieGenresRepository: IMovieGenresRepository
) : IMovieUseCase {
    private var imageBaseUrl: String? = null
    private val movieGenres = ArrayList<MovieGenre>()

    override suspend fun getUpcomingMovies(): Result<PagedItems> {
        setImageBaseUrl()
        getMovieGenres()

        val response = movieRepository.getUpcomingMovies().getOrElse { exception ->
            return Result.failure(exception)
        }.toDomain(imageBaseUrl, movieGenres)
        return Result.success(response)
    }

    override suspend fun getTopRatedMovies(): Result<PagedItems> {
        setImageBaseUrl()
        getMovieGenres()

        val response = movieRepository.getTopRatedMovies(language = DEFAULT_LANGUAGE).getOrElse { exception ->
            return Result.failure(exception)
        }.toDomain(imageBaseUrl, movieGenres)
        return Result.success(response)
    }

    override suspend fun getRecommendedMovies(language: String?, yearOfRelease: Int?): Result<PagedItems> {
        setImageBaseUrl()
        getMovieGenres()

        val response = movieRepository.getTopRatedMovies(language = language ?: DEFAULT_LANGUAGE, yearOfRelease = yearOfRelease).getOrElse { exception ->
            return Result.failure(exception)
        }.toDomain(imageBaseUrl, movieGenres)
        return Result.success(response)
    }

    override suspend fun getMovieVideos(movieId: Int, language: String?): Result<List<MovieVideo>> {

        val response = movieRepository.getVideosFromMovie(movieId).getOrElse { exception ->
            return Result.failure(exception)
        }
        val domainResponse = response.results.map { it.toDomain(response.id) }
        return Result.success(domainResponse)
    }

    private suspend fun setImageBaseUrl() {
        imageBaseUrl = imageBaseUrl ?: apiConfigRepository.getApiConfiguration().getOrNull()?.images?.baseUrl
    }

    private suspend fun getMovieGenres() {
        if (movieGenres.isNotEmpty()) {
            movieGenresRepository.getMovieGenres().getOrNull()?.genres?.map { it.toDomain() }?.let {
                if (it.isNotEmpty()) movieGenres.addAll(it)
            }
        }
    }
}
