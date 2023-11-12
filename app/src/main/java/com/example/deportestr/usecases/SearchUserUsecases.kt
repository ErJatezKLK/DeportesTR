package com.example.deportestr.usecases

import com.example.deportestr.ui.models.User
import retrofit2.Response

interface SearchUserUsecases {
    suspend fun searchUser(email: String, password: String): Response<User>
}
