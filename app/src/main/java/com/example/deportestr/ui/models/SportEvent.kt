package com.example.deportestr.ui.models

import java.sql.Timestamp

data class SportEvent(
    var id : Int,
    var date : Timestamp,
    var result : String,
    var location : String
)