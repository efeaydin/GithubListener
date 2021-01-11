package com.efe.githubrepolistener

import android.app.Application
import androidx.multidex.MultiDexApplication

class MainApplication: MultiDexApplication() {
    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}