package com.example.deportestr.usecases

import com.example.deportestr.ui.models.Athlete
import retrofit2.Response

interface SearchAthletesByTeamIdUsecases {
    suspend fun searchAthletesByTeamId(teamId: Int): Response<List<Athlete>>

}
