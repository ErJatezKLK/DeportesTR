package com.example.deportestr.ui.models

data class UserMessages(
    //Modelo de mensajes es lo mismo que hacer una entidad en java
    val author: String,
    val body: String,
    val isFromLocalUser: Boolean
)
