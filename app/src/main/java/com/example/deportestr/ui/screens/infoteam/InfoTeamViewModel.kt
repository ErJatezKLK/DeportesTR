package com.example.deportestr.ui.screens.infoteam

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deportestr.ui.models.Athlete
import com.example.deportestr.ui.models.Team
import com.example.deportestr.usecases.SearchAthletesByTeamIdUsecases
import com.example.deportestr.usecases.SearchTeamByIdUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoTeamViewModel @Inject constructor(
    private val searchTeamByIdUsecases: SearchTeamByIdUsecases,
    private val searchAthletesByTeamIdUsecases: SearchAthletesByTeamIdUsecases,
): ViewModel() {

    private var teamLoaded by mutableStateOf(false)
    var team: Team? = null
    var athletes: List<Athlete>? = null

    fun loadInfo(teamId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = searchTeamByIdUsecases.searchTeamById(teamId)
            val responseAthletes = searchAthletesByTeamIdUsecases.searchAthletesByTeamId(teamId)
            team = responseBody.body()
            athletes = responseAthletes.body()
            teamLoaded = true
            Log.i(ContentValues.TAG, "User loaded: $team")
        }
    }
}