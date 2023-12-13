package com.example.pawtopia.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.pawtopia.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBarScreen(
        route = "HOME",
        title = "Beranda",
        icon = Icons.Default.Home
    )

    data object Chat : BottomBarScreen(
        route = "CHAT",
        title = "Chat Bot",
        icon = Icons.Default.Chat
    )

    data object Favorites : BottomBarScreen(
        route = "FAVORITES",
        title = "Favorit",
        icon = Icons.Default.StarOutline
    )

    data object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "Profil",
        icon = Icons.Default.Person
    )


}