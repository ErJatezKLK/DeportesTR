import com.example.deportestr.datasource.DeportesRepository

import com.example.deportestr.datasource.remote.DeportesRemoteDataSource
import com.example.deportestr.ui.models.Sport
import com.example.deportestr.ui.models.Team
import com.example.deportestr.ui.models.User
import retrofit2.Response
import javax.inject.Inject

class DeportesRepositoryImpl @Inject constructor(
    private val deportesRemoteDataSource: DeportesRemoteDataSource
) : DeportesRepository {
    override suspend fun searchUser(email: String, password: String): Response<User> {
        return deportesRemoteDataSource.searchUser(email, password)
    }

    override suspend fun searchUserByEmail(email: String): Response<User> {
        return deportesRemoteDataSource.searchUserByEmail(email)
    }

    override suspend fun addUser(user: User): Response<Void> {
        return deportesRemoteDataSource.addUser(user)
    }

    override suspend fun searchAllSports(): Response<List<Sport>> {
        return deportesRemoteDataSource.searchAllSports()
    }

    override suspend fun deleteUser(email: String): Response<Void> {
        return deportesRemoteDataSource.deleteUser(email)
    }

    override suspend fun searchTeamsInAthletesBySport(sportId: Int): Response<List<Team>> {
        return deportesRemoteDataSource.searchTeamsInAthletesBySport(sportId)
    }

}
