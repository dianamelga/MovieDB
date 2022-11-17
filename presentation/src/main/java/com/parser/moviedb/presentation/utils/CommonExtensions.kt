package com.parser.moviedb.presentation.utils

import kotlin.math.round

fun String?.formatUrl(): String? {
    return this?.replace("http://", "https://")
}


fun Int?.getTimer(hhMM: Boolean=false): String {
    if (this == null) return "00:00"

    val seconds = this
    val ss = seconds % 60
    var hh = seconds / 60
    val mm = hh % 60
    hh = hh / 60

    val hour : String = if(!hhMM) { if (hh < 10) "0$hh" else hh.toString() }else {
        val h = round((this.toFloat() / 60) / 60).toInt()
        if(h < 10) "0$h" else h.toString()
    }
    val minute: String = if(!hhMM) { if (mm < 10) "0$mm" else mm.toString()}else {
        val min = round(this.toFloat() / 60).toInt()
        val finalMin = if(min >= 60) {
            (min % 60)
        }else {
            min
        }
        if(finalMin < 10) "0$finalMin" else finalMin.toString()
    }
    val second: String = if (ss < 10) "0$ss" else ss.toString()

    return if(hh > 0 && !hhMM) {
        "$hour:$minute:$second"
    }else if(hhMM) {
        "$hour:$minute"
    }else {
        "$minute:$second"
    }
}
