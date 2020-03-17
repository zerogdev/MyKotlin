package com.mysample.disneymotions.util

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    sealed class Error(val exception: Exception) : Result<Nothing>() {
        class RecoverableError(exception: Exception) : Error(exception)
        class NonRecoverableError(exception: Exception) :
            Error(exception)
    }
    object InProgress : Result<Nothing>()
}

fun selaedclassExample(result: Result<Int>) {

    val s : Result.Success<Int> = Result.Success<Int>(200)
//    val r : Result<Int> = Result.Error()//error

    when(result) {
        is Result.Success -> TODO()
        is Result.Error.RecoverableError -> TODO()
        is Result.Error.NonRecoverableError -> TODO()
        is Result.InProgress -> TODO()
    }.exhaustive
}


