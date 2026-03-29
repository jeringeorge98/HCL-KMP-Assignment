package org.example.project.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed interface NavEvent{
    data class ToDetailScreen(
        val countryName: String
    ): NavEvent

    data object Back: NavEvent
}

class NavCoordinator {

    private val _channel = Channel<NavEvent>(capacity = Channel.BUFFERED)
    val events = _channel.receiveAsFlow()

    fun toDetailScreen(countryName: String){
        _channel.trySend(NavEvent.ToDetailScreen(countryName))
    }
    fun back(){
        _channel.trySend(NavEvent.Back)
    }

}