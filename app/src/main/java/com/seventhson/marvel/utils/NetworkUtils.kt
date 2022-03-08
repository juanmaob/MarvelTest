package com.seventhson.marvel.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkUtils(private val context: Context) {

    fun hasConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        cm?.getNetworkCapabilities(cm.activeNetwork)?.run {
            return (hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN))
        }

        return false
    }

    fun hasNoConnection(): Boolean = !hasConnection()
}