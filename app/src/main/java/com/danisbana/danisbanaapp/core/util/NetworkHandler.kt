package com.danisbana.danisbanaapp.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response

object NetworkHandler {
    suspend fun <T> performOperationAsync(request: (suspend () -> Response<T>)): Deferred<Resource<T>> {
        return CoroutineScope(Dispatchers.IO).async {
            return@async try {
                val response = request.invoke()
                if(response.isSuccessful){
                    Resource.Success<T>(
                        data = response.body(),
                        code = response.code()
                    )
                }
                else {
                    Resource.Error<T>(
                        code = response.code(),
                        message = response.message()
                    )
                }
            } catch (e: Exception) {
                Resource.Error<T>(code = 500, message = e.message)
            }
        }
    }
}