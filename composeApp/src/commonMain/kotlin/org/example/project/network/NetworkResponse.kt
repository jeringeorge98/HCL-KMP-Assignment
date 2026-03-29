package org.example.project.network

sealed class NetworkResponse <T>{

    data class Success<T>(val data: T): NetworkResponse<T>()
    data class Error<T>(val message: String,val code:Int?=null): NetworkResponse<T>()
    object Loading: NetworkResponse<Nothing>()
}