package com.example.deportestr.datasource.remote

import com.example.deportestr.ui.models.Sport
import com.example.deportestr.ui.models.Team
import com.example.deportestr.ui.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DeportesAPI {
    @GET("user")
    suspend fun searchUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<User>

    @GET("userE")
    suspend fun searchUserByEmail(
       @Query("email") email: String
    ): Response<User>


    @POST("userP")
    suspend fun addUser(
       @Body user: User
    ): Response<Void>

    @DELETE("user")
    suspend fun deleteUser(
        @Query("email") email: String
    ): Response<Void>

    @GET("sports")
    suspend fun searchAllSports(): Response<List<Sport>>

    @GET("teams")
    suspend fun searchTeamsInAthletesBySport(
      @Query("sport")  sportId: Int
    ): Response<List<Team>>

}
