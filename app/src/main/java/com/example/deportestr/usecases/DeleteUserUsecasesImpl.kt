package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import retrofit2.Response
import javax.inject.Inject

class DeleteUserUsecasesImpl@Inject constructor(
    private val deportesRepository: DeportesRepository
): DeleteUserUsecases {

    override suspend fun deleteUser(email: String): Response<Void> {
        return deportesRepository.deleteUser(email)
    }
}