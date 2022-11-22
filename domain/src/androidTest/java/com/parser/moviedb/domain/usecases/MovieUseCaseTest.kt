package com.parser.moviedb.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.parser.moviedb.domain.usecases.interfaces.IMovieUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class MovieUseCaseTest {
    /**
     * @instantTaskExecutorRule
     * swaps the background executor used by the Architecture Components
     * with a different one that executes each task synchronously.
     */

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var movieUseCase: IMovieUseCase

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun getUpcomingMovies() {
        runBlocking {
            val response = movieUseCase.getUpcomingMovies()
            val hasPosterUrlPopulated = (response.getOrNull()?.results?.map { it.posterUrl != null }?.size ?: 0) > 0
            val hasGenresPopulated = (response.getOrNull()?.results?.map { it.genres.isNotEmpty() }?.size ?: 0) > 0
            assert(response.isSuccess && hasPosterUrlPopulated && hasGenresPopulated)
        }

    }

    @Test
    fun getTopRatedMovies() {
        runBlocking {
            val response = movieUseCase.getUpcomingMovies()
            val hasPosterUrlPopulated = (response.getOrNull()?.results?.map { it.posterUrl != null }?.size ?: 0) > 0
            val hasGenresPopulated = (response.getOrNull()?.results?.map { it.genres.isNotEmpty() }?.size ?: 0) > 0
            assert(response.isSuccess && hasPosterUrlPopulated && hasGenresPopulated)
        }
    }

    @Test
    fun getRecommendedMovies() {
        runBlocking {
            val response = movieUseCase.getUpcomingMovies()
            val hasPosterUrlPopulated = (response.getOrNull()?.results?.map { it.posterUrl != null }?.size ?: 0) > 0
            val hasGenresPopulated = (response.getOrNull()?.results?.map { it.genres.isNotEmpty() }?.size ?: 0) > 0
            assert(response.isSuccess && hasPosterUrlPopulated && hasGenresPopulated)
        }
    }

    @Test
    fun getMovieVideos() {
        runBlocking {
            val response = movieUseCase.getMovieVideos(movieId = 851644, language = "en")
            val youtubeId = response.getOrNull()?.firstOrNull {
                it.site.equals("youtube", true) &&
                    it.type.equals("trailer", true) &&
                    it.official
            }?.id ?: ""
            assert(response.isSuccess && youtubeId.isNotEmpty())
        }
    }
}
