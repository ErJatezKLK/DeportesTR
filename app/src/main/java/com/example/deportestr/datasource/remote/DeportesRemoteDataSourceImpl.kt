package com.example.deportestr.datasource.remote

import com.example.deportestr.ui.models.Sport
import com.example.deportestr.ui.models.SportEvent
import com.example.deportestr.ui.models.Team
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

    override suspend fun searchAllSports(): Response<List<Sport>> =
        withContext(dispatcherProvider.ioDispatcher){
            return@withContext deportesAPI.searchAllSports()
        }

    override suspend fun deleteUser(email: String): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher){
            return@withContext deportesAPI.deleteUser(email)
        }

    override suspend fun searchTeamsBySport(sportId: Int): Response<List<Team>> =
        withContext(dispatcherProvider.ioDispatcher){
            return@withContext deportesAPI.searchTeamsBySport(sportId)
        }

    override suspend fun searchEventBySport(sportId: Int): Response<List<SportEvent>> =
        withContext(dispatcherProvider.ioDispatcher){
            return@withContext deportesAPI.searchEventBySport(sportId)
        }

    override suspend fun changePassword(user: User, newPassword: String): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher){
            deportesAPI.changePassword(user, newPassword)
        }


}
