package com.example.deportestr.datasource.remote

import com.example.deportestr.ui.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DeportesAPI {
    @GET("user")
    suspend fun searchUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<User>

    @GET("user")
    suspend fun searchUserByEmail(
       @Query("email") email: String
    ): Response<User>

    @POST("user")
    fun addUser(
       @Body user: User
    ): Response<Void>
}