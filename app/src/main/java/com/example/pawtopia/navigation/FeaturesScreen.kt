package com.example.pawtopia.navigation

sealed class FeaturesScreen(val route: String) {
    data object Conversation : FeaturesScreen(route = "CONVERSATION")
    data object FindClinic : FeaturesScreen(route = "FIND_CLINIC") {
//        fun createRoute(doctorId: Int) = "FIND_CLINIC/$doctorId"
    }
    data object DetailClinic : FeaturesScreen(route = "DETAIL_CLINIC/{clinicId}") {
        fun createRoute(clinicId: Int) = "DETAIL_CLINIC/$clinicId"
    }
    data object FindDoctor : FeaturesScreen(route = "FIND_DOCTOR")
    data object DetailDoctor : FeaturesScreen(route = "DETAIL_DOCTOR/{doctorId}") {
        fun createRoute(doctorId: Int) = "DETAIL_DOCTOR/$doctorId"

    }
    data object PetDiagnosis : FeaturesScreen(route = "PET_DIAGNOSIS")
    data object SuspectDiagnosis : FeaturesScreen(route = "SUSPECT_DIAGNOSIS")
}