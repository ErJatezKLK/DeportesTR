package com.example.deportestr.usecases

import com.example.deportestr.ui.models.User
import retrofit2.Response

interface ChangePasswordUsecases {
    suspend fun changePassword(user: User?, newpassword: String): Response<Void>

}
