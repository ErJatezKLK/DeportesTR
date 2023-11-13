package com.example.deportestr.ui.screens.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(): ViewModel(){
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> =_userName

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    private val _loginEnabled = MutableLiveData<Boolean>()
    val loginEnabled: LiveData<Boolean> = _loginEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isloading: LiveData<Boolean> = _isLoading

    private val _isGoRegister = MutableLiveData<Boolean>()
    val isGoRegister: LiveData<Boolean> = _isGoRegister

    fun OnRegistrationChanged(email: String, password: String, repeatPassword: String) {
        _email.value = email
        _password.value = password
        _repeatPassword.value = repeatPassword
        _loginEnabled.value = isValidUser(email) && isValidPassword(password) && isEqualPassword(password, repeatPassword)
    }
    fun NameRegister(userName: String){
        _userName.value = userName
    }

    private fun isValidUser(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isEqualPassword(password: String, repeatPassword: String): Boolean =
        password == repeatPassword


    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

    suspend fun onRegisterSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }
}