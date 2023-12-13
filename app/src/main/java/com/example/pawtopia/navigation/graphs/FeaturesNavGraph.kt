package com.example.pawtopia.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.pawtopia.navigation.FeaturesScreen
import com.example.pawtopia.screen.features.chat.ChatScreen
import com.example.pawtopia.screen.features.clinic.DetailClinicScreen
import com.example.pawtopia.screen.features.clinic.FindClinicScreen
import com.example.pawtopia.screen.features.doctor.DetailDoctorScreen
import com.example.pawtopia.screen.features.doctor.FindDoctorScreen

fun NavGraphBuilder.featuresNavGraph(
    onFeaturesTitleChanged: (String) -> Unit,
    navController: NavHostController,
) {
    navigation(
        route = Graph.FEATURES,
        startDestination = FeaturesScreen.PetDiagnosis.route
    ) {
        composable(route = FeaturesScreen.PetDiagnosis.route) {

        }
        composable(route = FeaturesScreen.SuspectDiagnosis.route) {

        }
        composable(route = FeaturesScreen.FindDoctor.route) {
            FindDoctorScreen(
                navigateToDetailDoctor = {
                    navController.navigate(FeaturesScreen.DetailDoctor.route)
                },
            )
        }
        composable(route = FeaturesScreen.DetailDoctor.route) {
            DetailDoctorScreen()
        }
        composable(route = FeaturesScreen.FindClinic.route) {
            FindClinicScreen(
                navigateToDetailClinic = {
                    navController.navigate(FeaturesScreen.DetailClinic.route)
                },
                navigateUp = { navController.navigateUp() }
            )
        }
        composable(route = FeaturesScreen.DetailClinic.route) {
            DetailClinicScreen(navigateUp = { navController.navigateUp() })
        }
        composable(route = FeaturesScreen.Conversation.route) {
            ChatScreen()
            onFeaturesTitleChanged("Doctor Simatupang")
        }
    }
}