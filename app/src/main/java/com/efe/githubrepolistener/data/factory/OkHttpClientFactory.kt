package com.efe.githubrepolistener.data.factory

import com.efe.githubrepolistener.BuildConfig
import com.efe.githubrepolistener.data.remote.service.ConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpClientFactory {
    companion object {
        val defaultClient: OkHttpClient by lazy {
            if (BuildConfig.DEBUG) {
                OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(ConnectivityInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            } else {
                OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(ConnectivityInterceptor())
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build()
            }
        }
    }
}