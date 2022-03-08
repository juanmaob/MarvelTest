package com.seventhson.marvel.utils.extensions

import android.graphics.Color

fun String?.parseColor(): Int {
    val defaultColor = Color.LTGRAY

    return try {
        this?.let {
            val prefix = when (this.startsWith("#")) {
                true -> ""
                else -> "#"
            }
            Color.parseColor(prefix + it)
        } ?:run {
            defaultColor
        }

    } catch (e: Exception) {
        defaultColor
    }
}