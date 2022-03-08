package com.seventhson.marvel.utils.extensions

import java.text.Normalizer


fun String.equalTo(
    other: String?,
    ignoreCase: Boolean = false,
    ignoreAccents: Boolean = false
): Boolean =
    this.equals(
        other = if (ignoreAccents) other?.removeAccents() else other,
        ignoreCase = ignoreCase
    )

fun String.removeAccents() =
    Normalizer
        .normalize(this, Normalizer.Form.NFD)
        .replace(Regex("[^\\p{ASCII}]"), "")

fun String.remove(text: String) = this.replace(text, "")

fun String.urlToHttps() =
    this.replace("http", "https")