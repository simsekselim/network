package com.simsekselim.network.monitoring

sealed class Event {

    class ConnectivityEvent(val isConnected: Boolean) : Event()
}