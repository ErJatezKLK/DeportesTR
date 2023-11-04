package com.example.deportestr.ui.models

import java.sql.Date

data class Team(
    var id: Int,
    var name: String,
    var creationDate: Date,
    var pais: String,
    var deporte: Sport
) {
}