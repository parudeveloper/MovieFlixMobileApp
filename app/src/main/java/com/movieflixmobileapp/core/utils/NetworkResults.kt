package com.movieflixmobileapp.core.utils

sealed class NetworkResults<T>(val data: T? = null,val message : T? = null) {
    class Success<T>(data: T?) :NetworkResults<T>(data)
    class Error<T>(data: T?,message: T?) :NetworkResults<T>(data,message)
    class Loading<T>(data: T?) :NetworkResults<T>(data)

}