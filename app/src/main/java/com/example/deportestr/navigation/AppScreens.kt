package com.example.deportestr.navigation

sealed class AppScreens(val route: String){
    object LoginScreen: AppScreens("login")
    object RegistrationScreen: AppScreens("register")
    object HomeScreen: AppScreens("home")
    object ProfileScreen: AppScreens("profile")
    object FormulaScreen: AppScreens("formula")
    object FootballScreen: AppScreens("football")
    object TenisScreen: AppScreens("tenis")
    object MotoGpScreen: AppScreens("motogp")
    object BasketScreen: AppScreens("basket")
    object WrcScreen: AppScreens("wrc")
}
