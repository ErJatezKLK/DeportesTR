package com.example.deportestr.ui.models




data class User(
    //Modelo de usuario es lo mismo que hacer una entidad en java
    var id: Int?,
    var name: String?,
    var email: String?,
    var password: String,
    var team: List<Team>?,
    var players: List<Athlete>?
)
