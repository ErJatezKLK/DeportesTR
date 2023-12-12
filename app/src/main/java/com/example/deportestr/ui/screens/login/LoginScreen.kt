package com.example.deportestr.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deportestr.R
import kotlinx.coroutines.time.delay

/**
 * LoginScreen se encarga de poner en pantalla la interfaz
 */
@Composable
fun LoginScreen(
    goRegister: () -> Unit,
    goHome: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
    goForgot: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Login(Modifier.align(Alignment.Center), viewModel, goRegister, goHome, goForgot)
    }

}

/**
 * La estructura de la pantalla de login
 */
@Composable
fun Login(
    modifier: Modifier,
    viewModel: LoginViewModel,
    goRegister: () -> Unit,
    goHome: (String) -> Unit,
    goForgot: () -> Unit,
) {
    val email = viewModel.email
    val password = viewModel.password
    val loginEnabled = viewModel.loginEnabled
    viewModel.onLoginChange(email, password)

    //Estructura en columna que llama a otras funciones para un codigo mas limpio y ordenado

    Column(modifier = Modifier) {
        HeaderImageLogin()
        TitleTextLogIn(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(60.dp))
        UserEmailField(email, viewModel)
        Spacer(modifier = Modifier.padding(15.dp))
        PasswordField(password, viewModel)
        Text(
            text = "*La contraseña debe de ser de mas de 6 caracteres",
            color = Color.Red
        )
        Spacer(modifier = Modifier.padding(8.dp))
        ForgotPassword(Modifier.align(Alignment.End), goForgot)
        Spacer(modifier = Modifier.padding(16.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            LoginButton(goHome, viewModel, loginEnabled)
            RegisterButton(goRegister)
        }
    }
}

//La imagen del logo
@Composable
fun HeaderImageLogin() {
    Image(
        painter = painterResource(id = R.drawable.logo_bueno),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}

//El titulo de la aplicacion
@Composable
fun TitleTextLogIn(modifier: Modifier) {
    Text(
        text = "DeportesTR",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFB10404),
        modifier = modifier
    )
}

//el textfield del email
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserEmailField(email: String, viewModel: LoginViewModel) {
    TextField(
        value = email,
        onValueChange = { viewModel.email = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = stringResource(id = R.string.user)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        label = { Text(text = stringResource(id = R.string.email_insert)) }
    )
}

//El textfield de la contraseña con el trailing icon para que cambie al pulsar en el icono y
//esta sea visible
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String, viewModel: LoginViewModel) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { viewModel.password = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = stringResource(id = R.string.password)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
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
        label = { Text(text = stringResource(id = R.string.password_insert)) }
    )
}

//Olvidaste la contraseña en caso de olvidarse vas a esta pantalla y la cambias
@Composable
fun ForgotPassword(modifier: Modifier, goForgot: () -> Unit) {
    Text(
        text = stringResource(id = R.string.change_password),
        modifier = modifier
            .clickable { }
            .padding(end = 4.dp)
            .clickable { goForgot() },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFB10404)
    )
}

//El boton de inicio de sesion el cual lleva a la pantalla de home
@Composable
fun LoginButton(home: (String) -> Unit, viewModel: LoginViewModel, loginEnabled: Boolean) {
    val context = LocalContext.current
    val userLoaded = viewModel.userLoaded

    LaunchedEffect(userLoaded){
        viewModel.searchUser()
    }
    Button(
        onClick = {
            /**
             *  Al pulsar el boton si el usuario es nulo o no se ha encontrado
             *  por que se han introducido los datos mal saltaria un toast y saldria que el usuario
             *  no se a encontrado o no existe
             */

            viewModel.searchUser()
            val user = viewModel.user
            if (user != null) {
                home(user.email!!)
            } else {
                Toast.makeText(
                    context,
                    "No se ha encontrado el usuario",
                    Toast.LENGTH_LONG
                ).show()
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
        ), enabled = loginEnabled
    ) {
        Text(text = stringResource(id = R.string.sign_in))
    }
}

//El boton de registro el cual lleva a la pantalla de registro
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
        Text(text = stringResource(id = R.string.sign_up))
    }
}