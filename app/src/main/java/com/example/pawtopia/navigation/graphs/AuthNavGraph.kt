package com.example.pawtopia.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.pawtopia.screen.auth.login.LoginScreen
import com.example.pawtopia.screen.auth.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                navigateToRegister = {
                    navController.popBackStack()
                    navController.navigate(AuthScreen.SignUp.route)
                },
                navigateToHome = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                }
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            RegisterScreen(
                navigateToLogin = {
                    navController.popBackStack()
                    navController.navigate(AuthScreen.Login.route)
                },
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Login : AuthScreen(route = "LOGIN")
    data object SignUp : AuthScreen(route = "SIGN_UP")
}