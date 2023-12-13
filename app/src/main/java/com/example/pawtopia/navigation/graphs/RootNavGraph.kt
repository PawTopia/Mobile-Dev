package com.example.pawtopia.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pawtopia.screen.main.MainScreen
import com.example.pawtopia.screen.WelcomeScreen

@Composable
fun RootNavigationGraph(
    startDestination: String,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startDestination
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.ONBOARDING) {
            WelcomeScreen(
                navigateToAuth = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },
                onNavUp = { navController.navigateUp() }
            )
        }
        composable(route = Graph.MAIN) {
            MainScreen(
                navigateToAuth = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                }
            )
        }

    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "main_graph"
    const val ONBOARDING = "onboarding_graph"
    const val FEATURES = "features_graph"
}