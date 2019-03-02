package com.github.mbarrben.moviedb.network

import okhttp3.Interceptor
import okhttp3.Response

class DefaultHeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }
}