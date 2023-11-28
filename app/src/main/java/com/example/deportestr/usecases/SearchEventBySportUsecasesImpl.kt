package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.SportEvent
import retrofit2.Response
import javax.inject.Inject

class SearchEventBySportUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): SearchEventBySportUsecases{
    override suspend fun searchEventBySport(sportId: Int): Response<List<SportEvent>> {
        return deportesRepository.searchEventBySport(sportId)
    }
}