package com.example.deportestr.ui.models

import java.sql.Timestamp

data class SportEvent(
    //Modelo de partido/evento es lo mismo que hacer una entidad en java
    var id : Int,
    var date : Timestamp,
    var result : String,
    var location : String
)