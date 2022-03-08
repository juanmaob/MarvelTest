package com.seventhson.marvel.utils.extensions

import android.os.Handler
import android.os.Looper

inline fun runDelayed(delay: Long = 200L, crossinline block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ block() }, delay)
}