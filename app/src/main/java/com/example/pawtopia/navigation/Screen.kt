package com.example.pawtopia.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen(route = "welcome")
    object Login : Screen(route = "login")
    object Register : Screen(route = "register")
    object Home : Screen(route = "home")
    object Chat : Screen(route = "chat")
    object Favorite : Screen(route = "favorite")
    object Profile : Screen(route = "profile")
}