package com.example.deportestr.usecases

import com.example.deportestr.ui.models.User
import retrofit2.Response

interface SearchUserByEmailUseCases {

        suspend fun searchUserByEmail(email: String): Response<User>

}
