package com.example.deportestr.usecases

import retrofit2.Response

interface DeleteUserUsecases {
    suspend fun deleteUser(id: String): Response<Void>

}
