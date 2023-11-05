package com.example.deportestr.ui.screens.viewmodels

import androidx.compose.runtime.toMutableStateList
import com.example.deportestr.ui.models.UserMessages

class ConversationViewModel(
    val channelName: String,
    val channelMembers: Int,
    initialMessages: List<UserMessages>
) {
    private val _messages: MutableList<UserMessages> = initialMessages.toMutableStateList()
    val messages: List<UserMessages> = _messages

    fun addMessage(msg: UserMessages) {
        _messages.add(0, msg) // Se añade al principio de la lista
    }
}