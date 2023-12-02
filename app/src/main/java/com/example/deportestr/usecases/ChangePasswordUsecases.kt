package com.example.deportestr.usecases

import retrofit2.Response

interface ChangePasswordUsecases {
    suspend fun changePassword(email: String, newpassword: String): Response<Void>

}
