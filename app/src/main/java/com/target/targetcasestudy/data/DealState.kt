package com.target.targetcasestudy.data


/**
 * DealState is Sealed class that is used to hold different state like Loading, Error, Success
 */
sealed class DealsState<out T> {
    data class Loading(val isLoading: Boolean) : DealsState<Nothing>()
    data class Success<T>(val data: T) : DealsState<T>()
    data class Error(val error: Throwable) : DealsState<Nothing>()
}