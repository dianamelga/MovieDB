package com.parser.moviedb.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parser.moviedb.presentation.utils.readOnly
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel : ViewModel() {
    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState = _viewState.readOnly

    fun loadData() {
        _viewState.value = HomeViewState.LoadData.Processing
        // TODO
    }
}

sealed class HomeViewState {
    sealed class LoadData : HomeViewState() {
        object Processing : LoadData()
        data class Success(val data: Any) : LoadData()
        data class Failure(val error: Throwable) : LoadData()
    }
}