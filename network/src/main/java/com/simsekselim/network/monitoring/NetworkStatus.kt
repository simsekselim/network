package com.simsekselim.network.monitoring

/**
 * @Author: Selim Simsek
 * @Date: 10.02.2023
 */
sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}
