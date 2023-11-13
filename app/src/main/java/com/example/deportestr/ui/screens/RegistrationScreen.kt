package com.example.deportestr.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.deportestr.R
import com.example.deportestr.ui.screens.viewmodels.RegistrationViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    goLogin: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF303030))
    ) {
        Register(Modifier.align(Alignment.Center), viewModel, goLogin)
    }
}

@Composable
fun Register(modifier: Modifier, viewModel: RegistrationViewModel, goLogin: () -> Unit) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val name: String by viewModel.userName.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val repeatPassword: String by viewModel.repeatPassword.observeAsState(initial = "")
    val loginEnabled: Boolean by viewModel.loginEnabled.observeAsState(initial = false)

    val isLoading: Boolean by viewModel.isloading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
            Toast.makeText(context, "Guardando informacion", Toast.LENGTH_SHORT).show()
        }
    } else {

        Column(modifier = Modifier) {
            HeaderImageRegistration()
            SignTitle(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(15.dp))
            EmailRegister(email) { viewModel.OnRegistrationChanged(it, password, repeatPassword) }
            Spacer(modifier = Modifier.padding(15.dp))
            NameRegister(name) { viewModel.NameRegister(it) }
            Spacer(modifier = Modifier.padding(15.dp))
            PasswordRegister(password) { viewModel.OnRegistrationChanged(email, it, repeatPassword) }
            Spacer(modifier = Modifier.padding(15.dp))
            RepeatePassword(repeatPassword) { viewModel.OnRegistrationChanged(email, password, it) }
            Spacer(modifier = Modifier.padding(16.dp))
            LogButton(loginEnabled, goLogin) {
                coroutineScope.launch { viewModel.onLoginSelected() }
            }
        }
    }
}

@Composable
fun SignTitle(modifier: Modifier) {
    Text(
        text = "DeportesTR",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFB10404),
        modifier = modifier
    )
}

@Composable
fun HeaderImageRegistration() {
    Image(
        painter = painterResource(id = R.drawable.logo_bueno),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailRegister(user: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = user,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = "user@dd.com") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFF1D1D1D),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        label = { Text(text = "Introduce tu correo") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameRegister(name: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = "Nombre") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFF1D1D1D),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        label = { Text(text = "Introduce tu Nombre") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRegister(user: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = user,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = "contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFF1D1D1D),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        label = { Text(text = "Introduce tu contraseña") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatePassword(user: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = user,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = "Repite la contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFF1D1D1D),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        label = { Text(text = "Introduce tu contraseña") }
    )
}

@Composable
fun LogButton(loginEnabled: Boolean, goLogin: () -> Unit, register: () -> Job) {
    Button(
        onClick = { register() },
        modifier = Modifier
            .padding(5.dp)
            .width(IntrinsicSize.Max)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(0xFFFFFFFF),
            disabledContentColor = Color(0xFF882D2D),
            containerColor = Color(0xFF882D2D)
        ), enabled = loginEnabled
    ) {
        Text(text = "Sign In")
    }
}