package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.User
import retrofit2.Response
import javax.inject.Inject

class SearchUserByEmailUseCasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): SearchUserByEmailUseCases {

    override suspend fun searchUserByEmail(email: String): Response<User> {
        return deportesRepository.searchUserByEmail(email)
    }
}