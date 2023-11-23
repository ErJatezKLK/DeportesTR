package com.example.deportestr.ui.screens.wrc

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deportestr.ui.models.User
import com.example.deportestr.usecases.SearchUserByEmailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WrcViewModel  @Inject constructor(
    private val searchUserByEmailUseCases: SearchUserByEmailUseCases,
): ViewModel() {
    var userLoaded by mutableStateOf(false)
    var user: User? = null
    var email by mutableStateOf("")

    fun loadInfo(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = searchUserByEmailUseCases.searchUserByEmail(email)
            user = responseBody.body()
            userLoaded = true
            Log.i(ContentValues.TAG, "User loaded: $user")
        }
    }
}
