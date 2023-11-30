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
import com.example.deportestr.usecases.SearchUserUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val changePasswordUsecases: ChangePasswordUsecases,
    private val searchUserUsecases: SearchUserUsecases
): ViewModel() {
    var userLoaded = false
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var user: User? = null
    var reapeatPassword: String by mutableStateOf("")
    var loginEnabled by mutableStateOf(false)

    fun addUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = searchUserUsecases.searchUser(email, password)
            user = responseBody.body()
            userLoaded = true
            Log.i(ContentValues.TAG, "User loaded: $user")
            val userWithNewPassword = User(user!!.id, user!!.name, user!!.email, password, null, null)
            changePasswordUsecases.changePassword(userWithNewPassword)
        }
    }

    fun OnRegistrationChanged(email: String, password: String, repeatPassword: String) {
        this.email = email
        this.password = password
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