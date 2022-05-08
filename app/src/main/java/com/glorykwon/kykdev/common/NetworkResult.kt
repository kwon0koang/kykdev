package com.glorykwon.kykdev.common

sealed class NetworkResult {
    data class Success(val data: Any? = null
                       , val data2: Any? = null
                       , val data3: Any? = null
                       , val data4: Any? = null
                       , val data5: Any? = null): NetworkResult()
    data class Error(val throwable: Throwable, val message: String? = ""): NetworkResult()
    data class Loading(val message: String = "Loading"): NetworkResult()
}



