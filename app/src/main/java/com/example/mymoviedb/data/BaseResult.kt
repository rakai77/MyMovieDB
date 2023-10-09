package com.example.mymoviedb.data

sealed class BaseResult<out T> {
    data class Success<out T>(val data: T?) : BaseResult<T>()
    data class Error(val exception: Throwable) : BaseResult<Nothing>()
    object Loading : BaseResult<Nothing>()
}