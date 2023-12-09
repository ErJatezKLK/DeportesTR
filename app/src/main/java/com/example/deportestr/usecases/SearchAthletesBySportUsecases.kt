package com.example.deportestr.usecases

import com.example.deportestr.ui.models.Athlete
import retrofit2.Response

interface SearchAthletesBySportUsecases {
    suspend fun searchAthletesBySport(sportId: Int): Response<List<Athlete>>

}
