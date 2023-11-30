package com.example.deportestr.usecases

import com.example.deportestr.ui.models.User

interface ChangePasswordUsecases {
    suspend fun changePassword(user: User): Any

}
