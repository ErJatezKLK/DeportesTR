package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.Team
import retrofit2.Response
import javax.inject.Inject

class SearchTeamsBySportUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): SearchTeamsBySportUsecases {
    override suspend fun searchTeamsInAthletesBySport(sportId: Int): Response<List<Team>> {
        return deportesRepository.searchTeamsInAthletesBySport(sportId)
    }
}