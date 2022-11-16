package com.parser.moviedb.presentation.utils

fun String?.formatUrl(): String? {
    return this?.replace("http://", "https://")
}
