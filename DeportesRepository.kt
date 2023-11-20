package com.example.deportestr.datasource

import com.example.deportestr.ui.models.User
import retrofit2.Response

interface DeportesRepository {
    //SIEMPRE SUSPEND EN INTERFACES
    suspend fun searchUser(email: String, password: String): Response<User>
    suspend fun searchUserByEmail(email: String): Response<User>
    suspend fun addUser(user: User): Response<Void>

}