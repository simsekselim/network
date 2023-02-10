package com.simsekselim.network.monitoring

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

/**
 * @Author: Selim Simsek
 * @Date: 10.02.2023
 */

class NetworkStatusHelper(context: Context) : LiveData<NetworkStatus>() {

    val validatedNetworkConnections: ArrayList<Network> = ArrayList()
    var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback

    fun announceStatus() {
        if (validatedNetworkConnections.isNotEmpty()) {
            postValue(NetworkStatus.Available)
        } else {
            postValue(NetworkStatus.Unavailable)
        }
    }

    private fun getConnectivityManagerCallback() =
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                val networkCapability = connectivityManager.getNetworkCapabilities(network)
                val hasNetworkConnection =
                    networkCapability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        ?: false
                if (hasNetworkConnection) {
                    validatedNetworkConnections.add(network)
                    announceStatus()
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                validatedNetworkConnections.remove(network)
                announceStatus()
            }
        }

    override fun onActive() {
        super.onActive()
        announceStatus()
        connectivityManagerCallback = getConnectivityManagerCallback()
        val networkRequest = NetworkRequest
            .Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, connectivityManagerCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
    }

}