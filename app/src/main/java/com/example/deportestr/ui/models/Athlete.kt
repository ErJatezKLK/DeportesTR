package com.example.deportestr.ui.models

data class Athlete(
    var id : Int,
    var name : String,
    var surname : String,
    var position : Int,
    var age : Int,
    var nacionality : String,
    var nickname : String,
    var titles : Int,
    var team : Int,
    var sport : Sport
)
