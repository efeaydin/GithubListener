package com.efe.githubrepolistener.ui.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.efe.githubrepolistener.R
import com.efe.githubrepolistener.data.remote.service.ConnectivityInterceptor

abstract class BaseActivity: AppCompatActivity() {

    private var viewModel: ViewModel? = null

    abstract fun getViewModel(): ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel()

        LocalBroadcastManager.getInstance(this).registerReceiver(
            connectivityBroadcastManager,
            IntentFilter(ConnectivityInterceptor.KEY_NO_INTERNET_BROADCAST)
        )

    }

    override fun onDestroy() {
        super.onDestroy()

        LocalBroadcastManager.getInstance(this).unregisterReceiver(connectivityBroadcastManager)
    }

    private val connectivityBroadcastManager = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            showNoInternetConnectionMessage()
        }
    }

    private fun showNoInternetConnectionMessage() {
        AlertDialog.Builder(this).setTitle(R.string.error_dialog_title)
            .setMessage(R.string.no_internet_message)
            .setPositiveButton(R.string.error_dialog_button_ok, null).create().show()
    }

}