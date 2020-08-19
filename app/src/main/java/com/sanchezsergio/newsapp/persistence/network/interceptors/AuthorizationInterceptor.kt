package com.sanchezsergio.newsapp.persistence.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthorizationInterceptor: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val apiKey = "5da0473a70d446da8fa5865a4ddf3d86"

        request = request.newBuilder()
                .addHeader(AUTHORIZATION_HEADER_NAME, apiKey)
                .build()

        return chain.proceed(request)
    }

    companion object {

        const val AUTHORIZATION_HEADER_NAME = "Authorization"

    }


}