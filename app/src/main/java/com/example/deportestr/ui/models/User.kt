package com.example.deportestr.ui.models

import androidx.annotation.DrawableRes


data class User(
    var id : Int,
    var name : String,
    var email : String,
    var password : String,
    var team : List<Team>,
    @DrawableRes var photo: Int
)
