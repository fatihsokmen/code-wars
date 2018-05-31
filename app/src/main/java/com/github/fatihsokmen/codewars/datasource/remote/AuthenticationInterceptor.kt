package com.github.fatihsokmen.codewars.datasource.remote


import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
                .newBuilder()
                .header("Authorization", "onLA/WD8+gbsp2qIHlVFRBl1fjd+p6yv")

        return chain.proceed(builder.build())
    }
}
