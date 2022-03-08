package com.seventhson.marvel.data.common.base

import android.util.Log
import com.google.gson.Gson
import com.seventhson.marvel.data.common.model.response.ErrorResponse
import com.seventhson.marvel.utils.CustomException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class BaseRemoteDataSource {

    fun <T : Any> apiCall(call: suspend () -> Response<T>): Flow<T> = flow {

        val response = call.invoke()
        Log.d("TEST", response.toString())
        if (response.isSuccessful) {
            emit(response.body()!!)
        } else {
            //para controlar codigos de error en la app
            /*response.code().run {
                throw CustomException(
                    this, when (this) {
                        StatusCode.GenericError.code -> "mensaje de error custom"
                        StatusCode.HttpException.code -> "mensaje de error custom"
                        StatusCode.NoConnection.code -> "mensaje de error custom"
                        else -> "mensaje de error custom"
                    }
                )
            }*/

            val gson = Gson()
            response.errorBody()?.let {
                val errorResponse: ErrorResponse
                try {
                    errorResponse = gson.fromJson(it.string(), ErrorResponse::class.java)
                } catch (e: Exception) {
                    throw CustomException(response.code(), "Error ${response.code()}")
                }
                errorResponse?.let {
                    throw CustomException(errorResponse.status, errorResponse.error)
                }

            }
            throw CustomException(response.code(), "Error ${response.code()}")

        }

    }.flowOn(Dispatchers.IO)
}