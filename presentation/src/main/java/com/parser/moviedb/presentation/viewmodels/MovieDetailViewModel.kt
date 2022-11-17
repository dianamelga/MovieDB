package com.parser.moviedb.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parser.moviedb.domain.usecases.interfaces.IMovieUseCase
import com.parser.moviedb.presentation.utils.readOnly
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: IMovieUseCase
): ViewModel() {

    private val _viewState = MutableLiveData<MovieDetailViewState>()
    val viewState = _viewState.readOnly

    fun getMovieVideos(movieId: Int, language: String? = null) {
        _viewState.value = MovieDetailViewState.GetMovieVideos.Processing
        viewModelScope.launch {
            val response = movieUseCase.getMovieVideos(movieId, language)
            if (response.isSuccess) {
                val youtubeId = response.getOrNull()?.firstOrNull() {
                    it.site.equals("youtube", true) &&
                        it.type.equals("trailer", true) &&
                        it.official
                }?.id ?: ""
                _viewState.value = MovieDetailViewState.GetMovieVideos.Success(youtubeId)
            } else {
                _viewState.value = MovieDetailViewState.GetMovieVideos.Failure(response.exceptionOrNull() ?: Exception("Error getting videos"))
            }
        }
    }
}

sealed class MovieDetailViewState {
    sealed class GetMovieVideos : MovieDetailViewState() {
        object Processing : GetMovieVideos()
        data class Success(val youtubeId: String) : GetMovieVideos()
        data class Failure(val error: Throwable) : GetMovieVideos()
    }
}