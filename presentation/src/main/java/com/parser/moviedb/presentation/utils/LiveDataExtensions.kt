package com.parser.moviedb.presentation.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

val <T : Any> MutableLiveData<T>.readOnly: LiveData<T>
    get() = this