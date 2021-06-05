package com.example.footballleagueapplication.configs

import android.app.Application
import android.net.ConnectivityManager


class AppConfig : Application() {

    override fun onCreate() {
        super.onCreate()
    }


    init {
        instance = this
    }
    companion object {
        var instance: AppConfig? = null
            internal set
        fun hasNetwork(): Boolean {
            return instance!!.isNetworkConnected()
        }

    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }
}