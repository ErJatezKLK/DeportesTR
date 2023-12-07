package com.example.deportestr.ui.screens.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
/*
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatController: ChatController
): ViewModel() {
    private val state = MutableStateFlow(ChatlUiState())

    fun sendMessage(message: String){
        viewModelScope.launch {
            val chatMessage = chatController.trySendMessage(message)
            if(chatMessage != null){
                state.update {
                    it.copy(
                        messages = it.messages + chatMessage
                    )
                }
            }
        }
    }
}

 */