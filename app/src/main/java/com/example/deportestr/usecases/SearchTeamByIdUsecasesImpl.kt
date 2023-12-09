package com.example.deportestr.usecases

import androidx.compose.runtime.MutableIntState
import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.Team
import retrofit2.Response
import javax.inject.Inject

class SearchTeamByIdUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): SearchTeamByIdUsecases {
    override suspend fun searchTeamById(teamId: MutableIntState): Response<Team> {
        return deportesRepository.searchTeamById(teamId)
    }
}