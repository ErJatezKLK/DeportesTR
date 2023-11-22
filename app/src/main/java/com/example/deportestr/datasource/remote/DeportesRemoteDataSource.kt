package com.example.deportestr.datasource.remote

import com.example.deportestr.ui.models.Sport
import com.example.deportestr.ui.models.User
import retrofit2.Response

interface DeportesRemoteDataSource {
    suspend fun searchUser(email: String, password: String): Response<User>
    suspend fun searchUserByEmail(email: String): Response<User>
    suspend fun addUser(user: User): Response<Void>
    suspend fun searchAllSports(): Response<List<Sport>>
    suspend fun deleteUser(email: String): Response<Void>
}