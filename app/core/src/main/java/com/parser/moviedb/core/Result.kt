package com.parser.moviedb.core

sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()
    data class Failure(val exception: Exception): Result<Nothing>()
}
