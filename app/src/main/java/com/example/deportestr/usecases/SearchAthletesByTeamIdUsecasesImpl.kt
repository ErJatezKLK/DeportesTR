package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.Athlete
import retrofit2.Response
import javax.inject.Inject

class SearchAthletesByTeamIdUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): SearchAthletesByTeamIdUsecases {
    override suspend fun searchAthletesByTeamId(teamId: Int): Response<List<Athlete>> {
        return deportesRepository.searchAthletesByTeamId(teamId)
    }

}