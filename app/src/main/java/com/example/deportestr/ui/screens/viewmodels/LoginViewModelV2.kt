package com.example.deportestr.ui.screens.viewmodels

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModelV2 @Inject constructor(
    private val searchUserUsecases: SearchUserUsecases
): ViewModel() {
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var user: User? = null

    fun searchUser(): User? {
        viewModelScope.launch(Dispatchers.IO){
            val responseBody = searchUserUsecases.searchUser(email, password)
            user = responseBody.body()
        }
        return user
    }
}