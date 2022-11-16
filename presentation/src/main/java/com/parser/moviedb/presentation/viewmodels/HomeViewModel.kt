package com.parser.moviedb.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parser.moviedb.domain.entities.MediaItem
import com.parser.moviedb.domain.usecases.interfaces.IMovieUseCase
import com.parser.moviedb.presentation.R
import com.parser.moviedb.presentation.utils.readOnly
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel(
    @ApplicationContext private val context: Context,
    private val movieUseCase: IMovieUseCase
) : ViewModel() {
    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState = _viewState.readOnly

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
                )
            } else {
                _viewState.value = HomeViewState.LoadData.Failure(Exception(context.getString(R.string.error_retrieving_data)))
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
            val recommendedMovies: List<MediaItem>
            ) : LoadData()
        data class Failure(val error: Throwable) : LoadData()
    }
}