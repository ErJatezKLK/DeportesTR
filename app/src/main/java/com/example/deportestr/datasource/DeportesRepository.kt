package com.example.deportestr.datasource

import com.example.deportestr.ui.models.Sport
import com.example.deportestr.ui.models.SportEvent
import com.example.deportestr.ui.models.Team
import com.example.deportestr.ui.models.User
import retrofit2.Response

interface DeportesRepository {
    //SIEMPRE SUSPEND EN INTERFACES
    suspend fun searchUser(email: String, password: String): Response<User>
    suspend fun searchUserByEmail(email: String): Response<User>
    suspend fun addUser(user: User): Response<Void>
    suspend fun searchAllSports(): Response<List<Sport>>
    suspend fun deleteUser(email: String): Response<Void>
    suspend fun searchTeamsInAthletesBySport(sportId: Int): Response<List<Team>>
    suspend fun searchEventBySport(sportId: Int): Response<List<SportEvent>>

}
