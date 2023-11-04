package com.example.deportestr.ui.models

import androidx.annotation.DrawableRes

data class Sport(
    var id : Int,
    var name : String,
    @DrawableRes var photo: Int
)
