package com.example.deportestr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.deportestr.ui.screens.basket.BasketScreen
import com.example.deportestr.ui.screens.football.FootballScreen
import com.example.deportestr.ui.screens.formula.FormulaScreen
import com.example.deportestr.ui.screens.home.HomeScreen
import com.example.deportestr.ui.screens.login.LoginScreen
import com.example.deportestr.ui.screens.motogp.MotoGpScreen
import com.example.deportestr.ui.screens.profile.ProfileScreen
import com.example.deportestr.ui.screens.registration.RegisterScreen
import com.example.deportestr.ui.screens.tenis.TenisScreen
import com.example.deportestr.ui.screens.wrc.WrcScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(
                goRegister = { navController.navigate(route = AppScreens.RegistrationScreen.route) },
                goHome = { email ->
                    navController.navigate(route = AppScreens.HomeScreen.route + "/$email")
                }
            )
        }
        composable(route = AppScreens.RegistrationScreen.route) {
            RegisterScreen(goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) })
        }
        composable(route = AppScreens.HomeScreen.route + "/{email}") { backStackEntry ->
            HomeScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goProfile = { email ->
                    navController.navigate(route = AppScreens.ProfileScreen.route + "/$email") },
                goFormula = { email -> navController.navigate(route = AppScreens.FormulaScreen.route + "/$email") },
                goFootball = { email ->navController.navigate(route = AppScreens.FootballScreen.route + "/$email") },
                goTenis = { email -> navController.navigate(route = AppScreens.TenisScreen.route + "/$email") },
                goMotoGp = { email -> navController.navigate(route = AppScreens.MotoGpScreen.route + "/$email") },
                goBasket = { email -> navController.navigate(route = AppScreens.BasketScreen.route + "/$email") },
                goWrc = { email -> navController.navigate(route = AppScreens.WrcScreen.route + "/$email") },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }
        composable(route = AppScreens.ProfileScreen.route + "/{email}") {backStackEntry ->
            ProfileScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }
        composable(route = AppScreens.FormulaScreen.route + "/{email}") {backStackEntry ->
            FormulaScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }
        composable(route = AppScreens.FootballScreen.route + "/{email}") {backStackEntry ->
            FootballScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }
        composable(route = AppScreens.TenisScreen.route + "/{email}") {backStackEntry ->
            TenisScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }
        composable(route = AppScreens.MotoGpScreen.route + "/{email}") {backStackEntry ->
            MotoGpScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }
        composable(route = AppScreens.BasketScreen.route + "/{email}") {backStackEntry ->
            BasketScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }
        composable(route = AppScreens.WrcScreen.route + "/{email}") {backStackEntry ->
            WrcScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { navController.navigate(route = AppScreens.HomeScreen.route) },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }
    }
}