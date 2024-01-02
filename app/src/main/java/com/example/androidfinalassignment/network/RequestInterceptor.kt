package com.example.androidfinalassignment.network

import com.example.androidfinalassignment.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(private val headerName: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header(headerName, BuildConfig.API_KEY)
            .build()
        return chain.proceed(modifiedRequest)
    }
}