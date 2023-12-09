package com.example.deportestr.usecases

import androidx.compose.runtime.MutableIntState
import com.example.deportestr.ui.models.Team
import retrofit2.Response

interface SearchTeamByIdUsecases {
    suspend fun searchTeamById(teamId: MutableIntState): Response<Team>

}
