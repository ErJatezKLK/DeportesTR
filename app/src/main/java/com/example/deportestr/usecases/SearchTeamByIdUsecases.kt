package com.example.deportestr.usecases

import com.example.deportestr.ui.models.Team
import retrofit2.Response

interface SearchTeamByIdUsecases {
    suspend fun searchTeamById(teamId: Int): Response<Team>

}
