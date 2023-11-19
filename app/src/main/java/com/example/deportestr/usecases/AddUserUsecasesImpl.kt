package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import retrofit2.Response
import javax.inject.Inject

class AddUserUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): AddUserUseCases {
    override suspend fun addUser(): Response<Void> {
        return deportesRepository.addUser()
    }

}

