package com.example.deportestr.usecases

import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.ui.models.User
import retrofit2.Response
import javax.inject.Inject

class SearchUserUsecasesImpl @Inject constructor(
    private val deportesRepository: DeportesRepository
): SearchUserUsecases{
    override suspend fun searchUser(email: String, password: String): Response<User> {
        return deportesRepository.searchUser(email, password)
    }

}