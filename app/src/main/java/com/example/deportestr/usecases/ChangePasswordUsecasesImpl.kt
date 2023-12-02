package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import retrofit2.Response
import javax.inject.Inject

class ChangePasswordUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): ChangePasswordUsecases{

    override suspend fun changePassword(email: String, newpassword: String): Response<Void> {
        return deportesRepository.changePassword(email, newpassword)
    }
}