package com.example.deportestr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.deportestr.ui.screens.basket.BasketScreen
import com.example.deportestr.ui.screens.football.FootballScreen
import com.example.deportestr.ui.screens.forgotpassword.ForgotScreen
import com.example.deportestr.ui.screens.formula.FormulaScreen
import com.example.deportestr.ui.screens.home.HomeScreen
import com.example.deportestr.ui.screens.infoteam.InfoTeamScreen
import com.example.deportestr.ui.screens.login.LoginScreen
import com.example.deportestr.ui.screens.motogp.MotoGpScreen
import com.example.deportestr.ui.screens.profile.ProfileScreen
import com.example.deportestr.ui.screens.registration.RegisterScreen
import com.example.deportestr.ui.screens.tenis.TenisScreen
import com.example.deportestr.ui.screens.wrc.WrcScreen

/**
 * Esta es la navegacion que nos permite movernos entre las diferentes pantallas
 */
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        /**
         * Pantalla de login la pantalla que sale al inicio de la aplicacion y en la que se valida el usuario
         */
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(
                //Los parametros que le pasamos al login screen son otras pantallas con sus rutas
                //Para que en la funcion de la aplicacion navege a estas una vez clickado el boton o lo que haga navegar
                goRegister = { navController.navigate(route = AppScreens.RegistrationScreen.route) },
                goHome = { email ->
                    navController.navigate(route = AppScreens.HomeScreen.route + "/$email")
                },
                goForgot = { navController.navigate(route = AppScreens.ForgotScreen.route) }
            )
        }

        /**
         * Pantalla de registro en la cual tiene solo una navegacion
         */
        composable(route = AppScreens.RegistrationScreen.route) {
            //En este solo se necesita una navegacion por que navega de vuelta al inicio de sesion
            RegisterScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) }
            )
        }

        /**
         * Pantalla de Olvidar contraseña en la cual tiene una navegcion
         */
        composable(route = AppScreens.ForgotScreen.route) {
            //En este solo se necesita una navegacion por que navega de vuelta al inicio de sesion
            ForgotScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) }
            )
        }

        /**
         * Esta pantalla es la principal de la aplicacion es donde navegas a las pantallas de todos los deportes
         * lo que significa que tiene mas compnentes tambien se le pasa un email para que una vez iniciada esta pantalla
         * busque el usuario por email
         */
        composable(route = AppScreens.HomeScreen.route + "/{email}") { backStackEntry ->
            HomeScreen(
                // backStackEntry hace que le llegue el email a las demas pantallas y le llega el email
                // de la pantalla anterior
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goProfile = { email ->
                    navController.navigate(route = AppScreens.ProfileScreen.route + "/$email")
                },
                goFormula = { email -> navController.navigate(route = AppScreens.FormulaScreen.route + "/$email") },
                goFootball = { email -> navController.navigate(route = AppScreens.FootballScreen.route + "/$email") },
                goTenis = { email -> navController.navigate(route = AppScreens.TenisScreen.route + "/$email") },
                goMotoGp = { email -> navController.navigate(route = AppScreens.MotoGpScreen.route + "/$email") },
                goBasket = { email -> navController.navigate(route = AppScreens.BasketScreen.route + "/$email") },
                goWrc = { email -> navController.navigate(route = AppScreens.WrcScreen.route + "/$email") },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }

        /**
         * La pantalla de usuario que la cual se le pasa el email para que cargue el usuario en la pantalla
         */
        composable(route = AppScreens.ProfileScreen.route + "/{email}") { backStackEntry ->
            //Aqui tenemos el backStackEntry y el email como en la anterior
            ProfileScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { email -> navController.navigate(route = AppScreens.HomeScreen.route + "/$email") },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }

        /**
         * La pantalla de formula 1 la cual se le pasa el email para que cargue el usuario en la pantalla
         */
        composable(route = AppScreens.FormulaScreen.route + "/{email}") { backStackEntry ->
            FormulaScreen(
                //Aqui tenemos el backStackEntry y el email como en la anterior
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { email -> navController.navigate(route = AppScreens.HomeScreen.route + "/$email") },
                goInfoTeam = { teamId -> navController.navigate(route = AppScreens.TeamInfoScreen.route + "/$teamId") },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }

        /**
         * La pantalla de futbol la cual se le pasa el email cargue el usuario en la pantalla
         */
        composable(route = AppScreens.FootballScreen.route + "/{email}") { backStackEntry ->
            //Aqui tenemos el backStackEntry y el email como en la anterior
            FootballScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { email -> navController.navigate(route = AppScreens.HomeScreen.route + "/$email") },
                goInfoTeam = { teamId -> navController.navigate(route = AppScreens.TeamInfoScreen.route + "/$teamId") },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }

        /**
         * La pantalla de Tenis la cual se le pasa el email cargue el usuario en la pantalla
         */
        composable(route = AppScreens.TenisScreen.route + "/{email}") { backStackEntry ->
            //Aqui tenemos el backStackEntry y el email como en la anterior
            TenisScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { email -> navController.navigate(route = AppScreens.HomeScreen.route + "/$email") },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }

        /**
         * La pantalla de MotoGP la cual se le pasa el email cargue el usuario en la pantalla
         */
        composable(route = AppScreens.MotoGpScreen.route + "/{email}") { backStackEntry ->
            //Aqui tenemos el backStackEntry y el email como en la anterior
            MotoGpScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { email -> navController.navigate(route = AppScreens.HomeScreen.route + "/$email") },
                goInfoTeam = { teamId -> navController.navigate(route = AppScreens.TeamInfoScreen.route + "/$teamId") },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }

        /**
         * La pantalla de Baloncesto la cual se le pasa el email cargue el usuario en la pantalla
         */
        composable(route = AppScreens.BasketScreen.route + "/{email}") { backStackEntry ->
            //Aqui tenemos el backStackEntry y el email como en la anterior
            BasketScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { email -> navController.navigate(route = AppScreens.HomeScreen.route + "/$email") },
                goInfoTeam = { teamId -> navController.navigate(route = AppScreens.TeamInfoScreen.route + "/$teamId") },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }

        /**
         * La pantalla de WRC la cual se le pasa el email cargue el usuario en la pantalla
         */
        composable(route = AppScreens.WrcScreen.route + "/{email}") { backStackEntry ->
            //Aqui tenemos el backStackEntry y el email como en la anterior
            WrcScreen(
                goLogin = { navController.navigate(route = AppScreens.LoginScreen.route) },
                goHome = { email -> navController.navigate(route = AppScreens.HomeScreen.route + "/$email") },
                goInfoTeam = { teamId -> navController.navigate(route = AppScreens.TeamInfoScreen.route + "/$teamId") },
                email = backStackEntry.arguments?.getString("email") ?: "null"
            )
        }

        /**
         * La pantalla de Informacion de equipos la cual se le pasa el id del equipo cargue el los integrantes de este
         * Se puede navegar a esta pantalla desde todas la pantallas de los deportes
         */
        composable(
            route = AppScreens.TeamInfoScreen.route + "/{teamId}",
            arguments = listOf(
                navArgument("teamId"){ type = NavType.IntType}
            )
            ) { backStackEntry ->
            //Aqui tenemos el backStackEntry y el id del equipo
            InfoTeamScreen(
                goBack = { navController.popBackStack() },
                teamId = (backStackEntry.arguments?.getInt("teamId"))!!
            )
        }
    }
}