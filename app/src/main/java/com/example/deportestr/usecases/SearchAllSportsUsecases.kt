package com.example.deportestr.usecases

import com.example.deportestr.ui.models.Sport
import retrofit2.Response

interface SearchAllSportsUsecases {
    suspend fun searchAllSports(): Response<List<Sport>>

}
