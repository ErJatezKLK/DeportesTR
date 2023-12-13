package com.example.deportestr.ui.screens.login

import android.content.ContentValues.TAG
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deportestr.ui.models.User
import com.example.deportestr.usecases.SearchUserUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val searchUserUsecases: SearchUserUsecases
) : ViewModel() {
    var userLoaded = false
    var user: User? = null
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var loginEnabled by mutableStateOf(false)

    fun searchUser() {
        viewModelScope.launch(Dispatchers.IO) {
            awaitAll(
                async {
                    val responseBody = searchUserUsecases.searchUser(email, password)
                    if (responseBody.isSuccessful){
                        userLoaded = true
                        user = responseBody.body()
                    }
                    Log.i(TAG, "User loaded: $user $userLoaded")
                }
            )
        }
    }

    fun onLoginChange(email: String, password: String) {
        this.email = email
        this.password = password
        if (isValidEmail(email) && isValidPassword(password)) {
            loginEnabled = true
        } else {
            loginEnabled = false
        }
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length >= 6
}