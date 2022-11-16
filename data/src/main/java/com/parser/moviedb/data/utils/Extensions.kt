package com.parser.moviedb.data.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val ISO_FORMAT_8601 = "yyyy-MM-dd"
fun iso8601DateFormat() = SimpleDateFormat(ISO_FORMAT_8601, Locale.US)

fun String.toISO8601Date(): Date? {
    return try {
        iso8601DateFormat().parse(this)
    } catch (e: Exception) {
        null
    }
}