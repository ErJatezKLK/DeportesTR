package com.example.deportestr.datasource.remote

import com.example.deportestr.ui.models.Sport
import com.example.deportestr.ui.models.SportEvent
import com.example.deportestr.ui.models.Team
import com.example.deportestr.ui.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DeportesAPI {
    /**
     * Busca el usuario por email y contraseña
     */
    @GET("user")
    suspend fun searchUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<User>

    /**
     * Busca el usuario por email
     */
    @GET("user-email")
    suspend fun searchUserByEmail(
       @Query("email") email: String
    ): Response<User>

    /**
     * Crea un usuario
     */
    @POST("user-add")
    suspend fun addUser(
       @Body user: User
    ): Response<Void>

    /**
     * Borra un usuario pero desde aqui no funciona
     */
    @DELETE("user-del")
    suspend fun deleteUser(
        @Query("email") email: String
    ): Response<Void>

    /**
     *
     */
    @GET("sports")
    suspend fun searchAllSports(): Response<List<Sport>>

    @GET("teams")
    suspend fun searchTeamsInAthletesBySport(
      @Query("sportId")  sportId: Int
    ): Response<List<Team>>

    @GET("events")
    suspend fun searchEventBySport(
        @Query("sportId") sportId: Int
    ): Response<List<SportEvent>>

    @POST("user-update")
    suspend fun changePassword(
        @Query("email") email: String,
        @Query("newPassword") newPassword: String
    ): Response<Void>

}
