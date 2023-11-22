package com.example.deportestr.ui.screens.profile

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deportestr.ui.models.User
import com.example.deportestr.usecases.DeleteUserUsecases
import com.example.deportestr.usecases.SearchUserByEmailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProfieViewModel  @Inject constructor(
    private val searchUserByEmailUseCases: SearchUserByEmailUseCases,
    private val deleteUserUsecases: DeleteUserUsecases
) : ViewModel(){

    var userLoaded by mutableStateOf(false)
    var user: User? = null
    var email by mutableStateOf("")

    fun loadInfoUser(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = searchUserByEmailUseCases.searchUserByEmail(email)
            user = responseBody.body()
            userLoaded = true
            Log.i(ContentValues.TAG, "User loaded: $user")
        }
    }

    fun deleteUser(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserUsecases.deleteUser(email)
        }
    }

}