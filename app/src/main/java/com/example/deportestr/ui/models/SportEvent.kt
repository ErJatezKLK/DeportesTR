package com.example.deportestr.ui.models

data class SportEvent(
    var id : Int,
    var localTeam : Team,
    var visitingTeam : Team,
    var result : String,
    var location : String
) {
}