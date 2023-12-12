package com.example.pawtopia.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen(route = "welcome")
    object Login : Screen(route = "login")
    object Register : Screen(route = "register")
    object Home : Screen(route = "home")
    object ChatList : Screen(route = "chatList")
    object Chat : Screen(route = "chat")
    object Doctor : Screen(route = "doctor")
    object DoctorProfile : Screen(route = "doctorProfile")
    object Saved : Screen(route = "saved")
    object FindClinic : Screen(route = "clinic")
    object DetailClinic : Screen(route = "detailClinic")
    object Profile : Screen(route = "profile")
}