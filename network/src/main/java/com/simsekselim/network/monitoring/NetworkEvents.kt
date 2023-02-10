package com.simsekselim.network.monitoring

import androidx.lifecycle.LiveData
import com.simsekselim.network.monitoring.Event

/**
 * This liveData enabling network connectivity monitoring
 * @see ConnectivityStateHolder to get current connection state
 */
object NetworkEvents : LiveData<Event>() {

    internal fun notify(event: Event) {
        postValue(event)
    }

}