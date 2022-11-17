package com.parser.moviedb.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieGenre(
    val id: Int,
    val name: String
): Parcelable