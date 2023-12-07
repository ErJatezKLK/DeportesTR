package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.User
import retrofit2.Response
import javax.inject.Inject

class ChangePasswordUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): ChangePasswordUsecases{

    override suspend fun changePassword(user: User?, newpassword: String): Response<Void> {
        return deportesRepository.changePassword(user!!, newpassword)
    }
}