package com.example.deportestr.datasource.remote

import com.example.deportestr.ui.models.User
import retrofit2.Response

interface DeportesRemoteDataSource {
    suspend fun searchUser(email: String, password: String): Response<User>
    suspend fun searchUserByEmail(email: String): Response<User>
    suspend fun addUser(user): Response<Void>
}
