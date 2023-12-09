package com.example.deportestr.usecases

import androidx.compose.runtime.MutableIntState
import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.Athlete
import com.example.deportestr.ui.models.Team
import retrofit2.Response
import javax.inject.Inject

class SearchAthletesByTeamIdUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): SearchAthletesByTeamIdUsecases {
    override suspend fun searchAthletesByTeamId(teamId: MutableIntState): Response<List<Athlete>> {
        return deportesRepository.searchAthletesByTeamId(teamId)
    }

}