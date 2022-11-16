package com.parser.moviedb.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun goToMovieDetail() {
        _navigationState.value = NavigationState.MovieDetailScreen
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
    object MovieDetailScreen : NavigationState()
}
