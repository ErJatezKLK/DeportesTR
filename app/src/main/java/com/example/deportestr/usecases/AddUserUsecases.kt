package com.example.deportestr.usecases

import com.example.deportestr.ui.models.User
import retrofit2.Response

interface AddUserUsecases {
    suspend fun addUser(user: User): Response<Void>
}
