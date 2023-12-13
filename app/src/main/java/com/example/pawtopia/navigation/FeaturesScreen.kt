package com.example.pawtopia.navigation

sealed class FeaturesScreen(val route: String) {
    data object Conversation : FeaturesScreen(route = "CONVERSATION")
    data object FindClinic : FeaturesScreen(route = "FIND_CLINIC")
    data object DetailClinic : FeaturesScreen(route = "DETAIL_CLINIC")
    data object FindDoctor : FeaturesScreen(route = "FIND_DOCTOR")
    data object DetailDoctor : FeaturesScreen(route = "DETAIL_DOCTOR")
    data object PetDiagnosis : FeaturesScreen(route = "PET_DIAGNOSIS")
    data object SuspectDiagnosis : FeaturesScreen(route = "SUSPECT_DIAGNOSIS")
}