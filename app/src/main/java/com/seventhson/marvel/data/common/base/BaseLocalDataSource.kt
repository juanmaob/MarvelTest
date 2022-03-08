package com.seventhson.marvel.data.common.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseLocalDataSource {

    suspend fun <T : Any?> dbCall(call: suspend () -> T): T {
        return withContext(Dispatchers.IO) { call.invoke() }
    }
}