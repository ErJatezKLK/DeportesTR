package com.example.deportestr.usecases

import com.example.deportestr.ui.models.Team
import retrofit2.Response

interface SearchTeamsBySportUsecases {
    suspend fun searchTeamsInAthletesBySport(sportId: Int): Response<List<Team>>

}
