package com.danisbana.danisbanaapp.core.util

sealed class Resource<T>{
    data class Success<T>(
        val data: T?,
        val code: Int
    ): Resource<T>()

    data class Error<T>(
        val code: Int,
        val message: String?
    ): Resource<T>()
}