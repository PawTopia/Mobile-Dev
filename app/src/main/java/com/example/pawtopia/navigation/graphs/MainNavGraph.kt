package com.example.pawtopia.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pawtopia.navigation.BottomBarScreen
import com.example.pawtopia.navigation.FeaturesScreen
import com.example.pawtopia.screen.main.favorites.SavedScreen
import com.example.pawtopia.screen.main.historychat.HistoryChatScreen
import com.example.pawtopia.screen.main.home.HomeScreen
import com.example.pawtopia.screen.main.profile.ProfileScreen

@Composable
fun MainNavGraph(
    navigateToAuth: () -> Unit,
    onFeaturesTitleChanged: (String) -> Unit,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                navigateToFindDoctor = {
                    navController.navigate(FeaturesScreen.FindDoctor.route)
                },
                navigateToFindClinic = {
                    navController.navigate(FeaturesScreen.FindClinic.route)
                },
                navigateToSuspect = {
                    navController.navigate(FeaturesScreen.PetDiagnosis.route)
                },
                navigateToDetailClinic = { title, id ->
                    navController.navigate(FeaturesScreen.DetailClinic.createRoute(id))
                    onFeaturesTitleChanged(title)
                },
            )
        }
        composable(route = BottomBarScreen.Chat.route) {
            HistoryChatScreen(
                navigateToNewChat = {
                    navController.navigate(FeaturesScreen.FindDoctor.route)
                },
                navigateToChat = {
                    navController.navigate(FeaturesScreen.Conversation.route)
                    onFeaturesTitleChanged(it)
                })
        }
        composable(route = BottomBarScreen.Favorites.route) {
            SavedScreen(
                navigateToDetailClinic = { title, id ->
                    navController.navigate(FeaturesScreen.DetailClinic.createRoute(id))
                    onFeaturesTitleChanged(title)
                }
            )
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                navigateToLogin = navigateToAuth,
            )
        }
        featuresNavGraph(
            onFeaturesTitleChanged = onFeaturesTitleChanged,
            navController = navController
        )
    }
}