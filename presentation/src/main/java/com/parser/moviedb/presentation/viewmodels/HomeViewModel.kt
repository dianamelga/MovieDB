package com.parser.moviedb.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parser.moviedb.domain.entities.MediaItem
import com.parser.moviedb.domain.usecases.interfaces.IMovieUseCase
import com.parser.moviedb.presentation.R
import com.parser.moviedb.presentation.ui.adapters.MovieFilter
import com.parser.moviedb.presentation.ui.adapters.MovieFilterType
import com.parser.moviedb.presentation.utils.readOnly
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val movieUseCase: IMovieUseCase
) : ViewModel() {
    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState = _viewState.readOnly

    private var currentFilter = HashMap<MovieFilterType, String?>()

    fun loadData() {
        _viewState.value = HomeViewState.LoadData.Processing
        viewModelScope.launch {
            val upcomingMovies = async { movieUseCase.getUpcomingMovies() }
            val topRatedMovies = async { movieUseCase.getTopRatedMovies() }
            val (upcMov, topRatedMov) = listOf(upcomingMovies, topRatedMovies).awaitAll()

            if (upcMov.isSuccess && topRatedMov.isSuccess) {
                _viewState.value = HomeViewState.LoadData.Success(
                    upcomingMovies = upcMov.getOrNull()?.results ?: emptyList(),
                    topRatedMovies = topRatedMov.getOrNull()?.results ?: emptyList(),
                    recommendedMovies = upcMov.getOrNull()?.results?.take(6) ?: emptyList(),
                    filters = listOf(
                        MovieFilter("in Spanish", MovieFilterType.LANGUAGE,  filterValue = "es"),
                        MovieFilter("Released in 1993", MovieFilterType.YEAR_OF_RELEASE, filterValue = "1993")
                    )
                )
            } else {
                _viewState.value = HomeViewState.LoadData.Failure(Exception(context.getString(R.string.error_retrieving_data)))
            }
        }
    }

    fun filterRecommendedMovies(filter: MovieFilter) {
        _viewState.value = HomeViewState.FilterRecommendedMovies.Processing
        currentFilter[filter.type] = filter.filterValue

        viewModelScope.launch {
            val response = movieUseCase.getRecommendedMovies(language = currentFilter[MovieFilterType.LANGUAGE], yearOfRelease = currentFilter[MovieFilterType.YEAR_OF_RELEASE]?.toInt())
            if (response.isSuccess) {
                _viewState.value = HomeViewState.FilterRecommendedMovies.Success(response.getOrNull()?.results?.take(6) ?: emptyList())
            } else {
                _viewState.value = HomeViewState.FilterRecommendedMovies.Failure(response.exceptionOrNull() ?: Exception("Error filtering"))
            }
        }
    }
}

sealed class HomeViewState {
    sealed class LoadData : HomeViewState() {
        object Processing : LoadData()
        data class Success(
            val upcomingMovies: List<MediaItem>,
            val topRatedMovies: List<MediaItem>,
            val recommendedMovies: List<MediaItem>,
            val filters: List<MovieFilter>
            ) : LoadData()
        data class Failure(val error: Throwable) : LoadData()
    }

    sealed class FilterRecommendedMovies : HomeViewState() {
        object Processing : FilterRecommendedMovies()
        data class Success(
            val recommendedMoviesFiltered: List<MediaItem>
        ) : FilterRecommendedMovies()
        data class Failure(val error: Throwable) : FilterRecommendedMovies()
    }
}
