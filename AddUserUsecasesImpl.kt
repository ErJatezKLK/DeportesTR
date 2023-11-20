package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.User
import retrofit2.Response
import javax.inject.Inject

class AddUserUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): AddUserUseCases {
    override suspend fun addUser(user: User): Response<Void> {
        return deportesRepository.addUser(user)
    }

}

