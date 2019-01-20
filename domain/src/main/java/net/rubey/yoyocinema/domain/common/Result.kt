package net.rubey.yoyocinema.domain.common

sealed class Result<out T> {

    data class Success<T>(val value: T) : Result<T>()

    data class Error(val message: String?, val exception: Exception) : Result<Nothing>()
}