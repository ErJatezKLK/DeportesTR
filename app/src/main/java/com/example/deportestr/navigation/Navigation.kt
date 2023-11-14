package com.example.deportestr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.deportestr.ui.screens.BasketScreen
import com.example.deportestr.ui.screens.FootballScreen
import com.example.deportestr.ui.screens.FormulaScreen
import com.example.deportestr.ui.screens.HomeScreen
import com.example.deportestr.ui.screens.LoginScreen
import com.example.deportestr.ui.screens.MotoGpScreen
import com.example.deportestr.ui.screens.ProfileScreen
import com.example.deportestr.ui.screens.RegisterScreen
import com.example.deportestr.ui.screens.TenisScreen
import com.example.deportestr.ui.screens.WrcScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(
                goRegister = { navController.navigate(route = AppScreens.RegistrationScreen.route) },
                goHome = {
                    email ->
                    navController.popBackStack()
                    navController.navigate(route = AppScreens.HomeScreen.route + "/${email}")
                }
            )
        }
        composable(route = AppScreens.RegistrationScreen.route) {
            RegisterScreen(goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) })
        }
        composable(route = AppScreens.HomeScreen.route) { backStackEntry ->
            HomeScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goProfile = { navController.navigate(route = AppScreens.ProfileScreen.route) },
                goFormula = { navController.navigate(route = AppScreens.FormulaScreen.route) },
                goFootball = { navController.navigate(route = AppScreens.FootballScreen.route) },
                goTenis = { navController.navigate(route = AppScreens.TenisScreen.route) },
                goMotoGp = { navController.navigate(route = AppScreens.MotoGpScreen.route) },
                goBasket = { navController.navigate(route = AppScreens.BasketScreen.route) },
                goWrc = { navController.navigate(route = AppScreens.WrcScreen.route) },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }
        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) }
            )
        }
        composable(route = AppScreens.FormulaScreen.route) {
            FormulaScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) }
            )
        }
        composable(route = AppScreens.FootballScreen.route) {
            FootballScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) }
            )
        }
        composable(route = AppScreens.TenisScreen.route) {
            TenisScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) }
            )
        }
        composable(route = AppScreens.MotoGpScreen.route) {
            MotoGpScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) }
            )
        }
        composable(route = AppScreens.BasketScreen.route) {
            BasketScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) }
            )
        }
        composable(route = AppScreens.WrcScreen.route) {
            WrcScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) }
            )
        }
    }
}