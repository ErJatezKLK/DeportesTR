package com.example.deportestr.ui.screens.forgotpassword

import android.content.ContentValues
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deportestr.ui.models.User
import com.example.deportestr.usecases.ChangePasswordUsecases
import com.example.deportestr.usecases.SearchUserByEmailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val changePasswordUsecases: ChangePasswordUsecases,
    private val searchUserByEmailUseCases: SearchUserByEmailUseCases
) : ViewModel() {
    var userLoaded = false
    var email by mutableStateOf("")
    var newPassword by mutableStateOf("")
    var user: User? = null
    var reapeatPassword: String by mutableStateOf("")
    var loginEnabled by mutableStateOf(false)

    fun resetPassword() {
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = searchUserByEmailUseCases.searchUserByEmail(email)
            user = responseBody.body()
            changePasswordUsecases.changePassword(user, newPassword)
        }
    }

    fun onRegistrationChanged(email: String, password: String, repeatPassword: String) {
        this.email = email
        this.newPassword = password
        this.reapeatPassword = repeatPassword
        loginEnabled = isValidEmail(email) && isValidPassword(password) && isEqualPassword(
            password,
            repeatPassword
        )
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isEqualPassword(password: String, repeatPassword: String): Boolean =
        password == repeatPassword
}