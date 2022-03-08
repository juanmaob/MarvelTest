package com.seventhson.marvel.utils.extensions

inline fun <A, B, C, R> ifNotNull(first: A?, second: B?, third: C?, code: (A, B, C) -> R) {
    if (first != null && second != null && third != null) {
        code(first, second, third)
    }
}

inline fun <A, B, R> ifNotNull(first: A?, second: B?, code: (A, B) -> R) {
    if (first != null && second != null) {
        code(first, second)
    }
}