package com.example.deportestr.datasource.remote

import com.example.deportestr.ui.models.User
import com.example.deportestr.util.DispatcherProvider
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DeportesRemoteDataSourceImpl @Inject constructor(
    private val deportesAPI: DeportesAPI,
    private val dispatcherProvider: DispatcherProvider
): DeportesRemoteDataSource {
    override suspend fun searchUser(email: String, password: String): Response<User> =
        withContext(dispatcherProvider.ioDispatcher){
            return@withContext deportesAPI.searchUser(email, password)
        }

    override suspend fun searchUserByEmail(email: String): Response<User> =
        withContext(dispatcherProvider.ioDispatcher){
            return@withContext deportesAPI.searchUserByEmail(email)
        }

    override suspend fun addUser(user: User): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher){
            deportesAPI.addUser(user)
        }

}