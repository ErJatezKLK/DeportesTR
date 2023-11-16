package com.example.deportestr.ui.screens.viewmodels

import android.util.Log
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
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class LoginViewModelV2 @Inject constructor(
    private val searchUserUsecases: SearchUserUsecases
) : ViewModel() {
    var user: User? = null
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")


    fun searchUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = searchUserUsecases.searchUser(email, password)
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    user = response.body()
                }
            }
        }
    }
}