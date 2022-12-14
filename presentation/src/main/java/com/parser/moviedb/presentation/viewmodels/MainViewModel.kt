package com.parser.moviedb.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parser.moviedb.domain.entities.MediaItem
import com.parser.moviedb.presentation.utils.readOnly
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _navigationState = MutableLiveData<NavigationState>()
    val navigationState = _navigationState.readOnly

    fun goToHome() {
        _navigationState.value = NavigationState.HomeScreen
    }

    fun goToMovieDetail(mediaItem: MediaItem) {
        _navigationState.value = NavigationState.MovieDetailScreen(mediaItem)
    }

    fun openVideoPlayer(youtubeId: String) {
        _navigationState.value = NavigationState.VideoPlayerScreen(youtubeId)
    }
}

/**
 * this class is in charge of represent the navigation of the app,
 * usually, I create a navigationState per flow, in this particular case we
 * have a single flow, for this reason I planned to use a single activity
 * with this viewmodel to handle the navigation state.
 */
sealed class NavigationState {
    object HomeScreen : NavigationState()
    data class MovieDetailScreen(val mediaItem: MediaItem) : NavigationState()
    data class VideoPlayerScreen(val youtubeId: String) : NavigationState()
}
