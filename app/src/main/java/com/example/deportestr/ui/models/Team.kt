package com.example.deportestr.ui.models


import java.sql.Timestamp

data class Team(
    var id: Int,
    var name: String,
    var creationDate: Timestamp,
    var pais: String
)