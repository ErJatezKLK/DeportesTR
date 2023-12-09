package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.Athlete
import retrofit2.Response
import javax.inject.Inject

class SearchAthletesBySportUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): SearchAthletesBySportUsecases {
    override suspend fun searchAthletesBySport(sportId: Int): Response<List<Athlete>> {
        return deportesRepository.searchAthletesBySport(sportId)
    }

}