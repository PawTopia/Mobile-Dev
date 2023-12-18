package com.example.pawtopia.navigation.graphs

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.pawtopia.navigation.BottomBarScreen
import com.example.pawtopia.navigation.FeaturesScreen
import com.example.pawtopia.screen.features.chat.ChatScreen
import com.example.pawtopia.screen.features.clinic.DetailClinicScreen
import com.example.pawtopia.screen.features.clinic.FindClinicScreen
import com.example.pawtopia.screen.features.doctor.DetailDoctorScreen
import com.example.pawtopia.screen.features.doctor.FindDoctorScreen
import com.example.pawtopia.screen.features.suspect.SuspectedDiagnosisScreen
import com.example.pawtopia.screen.features.suspect.pet_diagnosis.PetDiagnosisScreen

fun NavGraphBuilder.featuresNavGraph(
    onFeaturesTitleChanged: (String) -> Unit,
    navController: NavHostController,
) {
    navigation(
        route = Graph.FEATURES,
        startDestination = FeaturesScreen.PetDiagnosis.route
    ) {
        composable(route = FeaturesScreen.PetDiagnosis.route) {
            PetDiagnosisScreen(navigateToSuspect = { prediction, description, treatment ->
                navController.navigate(
                    FeaturesScreen.SuspectDiagnosis.createRoute(
                        prediction,
                        description,
                        treatment
                    )
                )
            })
            onFeaturesTitleChanged("Cek Gejala")
        }
        composable(
            route = FeaturesScreen.SuspectDiagnosis.route,
            arguments = listOf(
                navArgument("prediction") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("treatment") { type = NavType.StringType },
            )
        ) {
            val prediction = it.arguments?.getString("prediction") ?: "Hasil Prediksi"
            val description = it.arguments?.getString("description") ?: "Hasil Deskripsi"
            val treatment = it.arguments?.getString("treatment") ?: "Hasil Treatment"
            SuspectedDiagnosisScreen(
                prediction = prediction,
                description = description,
                treatment = treatment,
                navigateToFindClinic = { navController.navigate(FeaturesScreen.FindClinic.route) },
                navigateToFindDoctor = { navController.navigate(FeaturesScreen.FindDoctor.route) },
                navigateToHome = { navController.navigate(Graph.MAIN) },
            )
            onFeaturesTitleChanged("Suspect Penyakit")
        }
        composable(
            route = FeaturesScreen.FindDoctor.route,
        ) {
            FindDoctorScreen(
                navigateToDetailDoctor = {
                    navController.navigate(FeaturesScreen.DetailDoctor.createRoute(it))
                },
            )
            onFeaturesTitleChanged("Pilih Dokter Terbaikmu")

        }
        composable(
            route = FeaturesScreen.DetailDoctor.route,
            arguments = listOf(navArgument("doctorId") { type = NavType.IntType })
        ) {
            val doctorId = it.arguments?.getInt("doctorId") ?: -1
            DetailDoctorScreen(
                doctorId = doctorId,
                navigateToConversation = { title ->
                    navController.navigate(FeaturesScreen.Conversation.route)
                    onFeaturesTitleChanged(title)
                }
            )
            LaunchedEffect(true) {
                onFeaturesTitleChanged("Profil Dokter")
            }
        }
        composable(route = FeaturesScreen.FindClinic.route) {
            FindClinicScreen(
                navigateToDetailClinic = { title, id ->
                    navController.navigate(FeaturesScreen.DetailClinic.createRoute(id))
                    onFeaturesTitleChanged(title)
                },
                navigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = FeaturesScreen.DetailClinic.route,
            arguments = listOf(navArgument("clinicId") { type = NavType.IntType })
        ) {
            val clinidId = it.arguments?.getInt("clinicId") ?: -1
            DetailClinicScreen(clinidId)
        }
        composable(route = FeaturesScreen.Conversation.route) {
            ChatScreen()
        }
    }
}