package com.glorykwon.kykdev.retrofittest

import okhttp3.Interceptor
import okhttp3.Response

class DeepSearchAuthenticationInterceptor(private val AUTO_TOKEN: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder()
            .header("Authorization", "Basic $AUTO_TOKEN")

        val request = builder.build()
        return chain.proceed(request)
    }
}