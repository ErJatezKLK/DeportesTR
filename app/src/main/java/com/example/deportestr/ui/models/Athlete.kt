package com.example.deportestr.ui.models

data class Athlete(
    //Modelo de deportista es lo mismo que hacer una entidad en java
    var id : Int,
    var name : String,
    var surname : String,
    var position : String,
    var age : Int,
    var nationality : String,
    var nickname : String,
    var titles : Int,
    var team : Team,
    var sport : Sport
)
