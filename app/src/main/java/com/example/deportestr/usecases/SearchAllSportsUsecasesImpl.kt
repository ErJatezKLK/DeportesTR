package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.Sport
import retrofit2.Response
import javax.inject.Inject

class SearchAllSportsUsecasesImpl@Inject constructor(
    private val deportesRepository: DeportesRepository
): SearchAllSportsUsecases {
    override suspend fun searchAllSports(): Response<List<Sport>> {
        return deportesRepository.searchAllSports()
    }

}