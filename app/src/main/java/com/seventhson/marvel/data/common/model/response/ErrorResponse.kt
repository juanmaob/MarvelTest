package com.seventhson.marvel.data.common.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val error: String = "",
    @SerializedName("code")
    val status: Int = 0
)