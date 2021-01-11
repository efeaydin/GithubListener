package com.efe.githubrepolistener.data.factory

import com.efe.githubrepolistener.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    companion object {
        val defaultRetrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(OkHttpClientFactory.defaultClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}