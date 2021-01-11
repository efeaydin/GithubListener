package com.efe.githubrepolistener.data.remote.service

import android.content.Intent
import android.os.Build
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.efe.githubrepolistener.MainApplication
import com.efe.githubrepolistener.utils.extensions.hasInternetConnection
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ConnectivityInterceptor : Interceptor {
    companion object {
        val KEY_NO_INTERNET_BROADCAST = "noInternetConnection"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return chain.proceed(chain.request())
        }

        return if (MainApplication.instance.hasInternetConnection()) {
            chain.proceed(chain.request())
        } else {
            sendBroadcast()
            Response.Builder().request(chain.request())
                .message("No Internet Connection").body("".toResponseBody(null))
                .code(499)
                .protocol(Protocol.HTTP_2).build()
        }
    }

    private fun sendBroadcast() {
        LocalBroadcastManager.getInstance(MainApplication.instance).sendBroadcast(
            Intent(
                KEY_NO_INTERNET_BROADCAST
            )
        )
    }
}