package com.example.deportestr.datasource.remote

import com.example.deportestr.ui.models.User
import retrofit2.Response

interface DeportesRemoteDataSource {
    suspend fun searchUser(email: String, password: String): Response<User>
}