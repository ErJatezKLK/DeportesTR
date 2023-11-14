package com.example.deportestr.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deportestr.R
import com.example.deportestr.ui.models.User
import com.example.deportestr.ui.screens.viewmodels.LoginViewModelV2

@Composable
fun LoginScreen(
    goRegister: () -> Unit,
    goHome: (String) -> Unit,
    viewModel: LoginViewModelV2 = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF303030))
    ) {
        Login(Modifier.align(Alignment.Center), viewModel, goRegister, goHome)
    }

}

@Composable
fun Login(
    modifier: Modifier,
    viewModel: LoginViewModelV2,
    goRegister: () -> Unit,
    goHome: (String) -> Unit,
) {
    val email = viewModel.email
    val password = viewModel.password

    Column(modifier = Modifier) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, start = 10.dp)
        ) {
            TitleText()
        }
        Spacer(modifier = Modifier.padding(60.dp))
        UserNameField(email, viewModel)
        Spacer(modifier = Modifier.padding(15.dp))
        PasswordField(password, viewModel)
        Spacer(modifier = Modifier.padding(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.padding(16.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            LoginButton(goHome, viewModel)
            RegisterButton(goRegister)


        }
    }
}


@Composable
fun TitleText() {
    Text(
        text = "Deportes",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFB10404),
        modifier = Modifier.padding(start = 4.dp)
    )
    Text(
        text = "TR",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF000000),
        modifier = Modifier.padding(start = 4.dp)
    )
    Image(
        painter = painterResource(id = R.drawable.logo_bueno),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameField(email: String, viewModelV2: LoginViewModelV2) {
    TextField(
        value = email,
        onValueChange = { viewModelV2.email = it },
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
fun PasswordField(password: String, viewModel: LoginViewModelV2) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { viewModel.password = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = "contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFF1D1D1D),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "show")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        label = { Text(text = "Introduce tu contraseña") }
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "¿Olvidaste tu crontraseña eh?",
        modifier = modifier
            .clickable { }
            .padding(end = 4.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFB10404)
    )
}

@Composable
fun LoginButton(home: (String) -> Unit, viewModel: LoginViewModelV2) {
    val user = viewModel.user
    val context = LocalContext.current

    Button(
        onClick = {
            viewModel.searchUser()
            if (user == null) {
                Toast.makeText(
                    context,
                    "No se ha encontrado el usuario",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                home(user.email)
            }
        },
        modifier = Modifier
            .padding(5.dp)
            .width(IntrinsicSize.Max)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(0xFFFFFFFF),
            disabledContentColor = Color(0xFF882D2D),
            containerColor = Color(0xFF882D2D)
        )
    ) {
        Text(text = "Iniciar sesion")
    }
}

@Composable
fun RegisterButton(goRegister: () -> Unit) {
    Button(
        onClick = { goRegister() },
        modifier = Modifier
            .padding(5.dp)
            .width(IntrinsicSize.Max)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(0xFFFFFFFF),
            disabledContentColor = Color(0xFF882D2D),
            containerColor = Color(0xFF882D2D)
        )
    ) {
        Text(text = "Registrarse")
    }
}


