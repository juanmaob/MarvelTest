package com.seventhson.marvel.utils

import com.seventhson.marvel.utils.extensions.urlToHttps

fun setImageUrl(path: String, extension: String) =
    "$path/landscape_incredible.$extension".urlToHttps()