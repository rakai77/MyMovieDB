package com.example.mymoviedb.core

sealed class BaseResult<out T> {
    data class Success<out T>(val data: T) : BaseResult<T>()
    data class Error(val message: String) : BaseResult<Nothing>()
    object Loading : BaseResult<Nothing>()
}