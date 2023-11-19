package com.example.deportestr.ui.screens.home

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deportestr.ui.models.User
import com.example.deportestr.ui.models.UserMessages
import com.example.deportestr.usecases.SearchUserByEmailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val searchUserByEmailUseCases: SearchUserByEmailUseCases
): ViewModel() {
    var userLoaded = false
    var user: User? = null
    var email: String by mutableStateOf("")

    fun searchUser() {
        viewModelScope.launch(Dispatchers.IO) {
            awaitAll(
                async {
                    val responseBody = searchUserByEmailUseCases.searchUserByEmail(email)
                    user = responseBody.body()
                    userLoaded = true
                    Log.i(ContentValues.TAG, "User loaded: $user")
                }
            )
        }
    }
}