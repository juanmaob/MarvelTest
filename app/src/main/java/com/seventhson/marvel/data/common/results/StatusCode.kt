package com.seventhson.marvel.data.common.results

enum class StatusCode(val code: Int) {
    GenericError(0),
    NoConnection(1001),
    OK(200),
    HttpException(404)
}