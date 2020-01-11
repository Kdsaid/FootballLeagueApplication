package com.example.footballleagueapplication

import android.net.ConnectivityManager
import android.app.Application
import android.content.Context


class MyApplication : Application() {

    private val isNetworkConnected: Boolean
        get() {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

    override fun onCreate() {
        super.onCreate()

        if (instance == null) {
            instance = this
        }
    }

    companion object {
        var instance: MyApplication? = null
            private set

        fun hasNetwork(): Boolean {
            return instance!!.isNetworkConnected
        }
    }
}
