package com.example.deportestr.ui.screens.basket

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deportestr.ui.models.SportEvent
import com.example.deportestr.ui.models.Team
import com.example.deportestr.ui.models.User
import com.example.deportestr.usecases.SearchEventBySportUsecases
import com.example.deportestr.usecases.SearchTeamsBySportUsecases
import com.example.deportestr.usecases.SearchUserByEmailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val searchUserByEmailUseCases: SearchUserByEmailUseCases,
    private val searchTeamsBySportUsecases : SearchTeamsBySportUsecases,
    private val searchEventsBySportUsecases : SearchEventBySportUsecases
) : ViewModel() {
    var userLoaded by mutableStateOf(false)
    var user: User? = null
    var teams: List<Team>? = null
    var events: List<SportEvent>? = null
    var email by mutableStateOf("")
    private var sportId = 5

    fun loadInfo(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = searchUserByEmailUseCases.searchUserByEmail(email)
            val responseTeams = searchTeamsBySportUsecases.searchTeamsInAthletesBySport(sportId)
            val responseEvents = searchEventsBySportUsecases.searchEventBySport(sportId)
            user = responseBody.body()
            teams = responseTeams.body()
            events = responseEvents.body()
            userLoaded = true
            Log.i(ContentValues.TAG, "User loaded: $user")
        }
    }
}
