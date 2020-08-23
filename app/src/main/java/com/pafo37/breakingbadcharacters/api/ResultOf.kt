package com.pafo37.breakingbadcharacters.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class ResultOf<out R> {
    data class Success<out T>(val data: T) : ResultOf<T>()
    data class Error(val exception: Exception, val message: String? = null) : ResultOf<Nothing>()
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResultOf<T> = withContext(Dispatchers.IO) {
    try {
        ResultOf.Success(apiCall.invoke())
    } catch (exception: Exception) {
        ResultOf.Error(exception, exception.message)
    }

}