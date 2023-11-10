package com.example.deportestr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deportestr.ui.models.User
import com.example.deportestr.ui.models.UserMessages
import com.example.deportestr.ui.screens.FormulaScreen
import com.example.deportestr.ui.screens.HomeScreen
import com.example.deportestr.ui.screens.LoginScreen
import com.example.deportestr.ui.screens.ProfileScreen
import com.example.deportestr.ui.screens.RegisterScreen
import com.example.deportestr.ui.screens.viewmodels.LoginViewModel
import com.example.deportestr.ui.screens.viewmodels.RegistrationViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(
                goRegister = navController,
                goHome = navController,
                viewModel = LoginViewModel()
            )
        }
        composable(route = AppScreens.RegistrationScreen.route) {
            RegisterScreen(viewModel = RegistrationViewModel(), goLogin = navController)
        }
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                goLogin = navController,
                goProfile = navController,
                goFormula = navController
            )
        }
        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen(goRegister = navController, goHome = navController)
        }
        composable(route = AppScreens.FormulaScreen.route) {
            FormulaScreen(goLogin = navController, goHome = navController)
        }
    }
}