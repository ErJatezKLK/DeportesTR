package com.example.deportestr.usecases

import androidx.compose.runtime.MutableIntState
import com.example.deportestr.ui.models.Athlete
import retrofit2.Response

interface SearchAthletesByTeamIdUsecases {
    suspend fun searchAthletesByTeamId(teamId: MutableIntState): Response<List<Athlete>>

}
