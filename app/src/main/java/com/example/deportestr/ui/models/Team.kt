package com.example.deportestr.ui.models


import java.sql.Timestamp

data class Team(
    //Modelo de equipo es lo mismo que hacer una entidad en java
    var id: Int?,
    var name: String,
    var creationDate: Timestamp,
    var country: String
)