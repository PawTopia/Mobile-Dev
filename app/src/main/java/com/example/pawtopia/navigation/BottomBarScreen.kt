package com.example.pawtopia.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.example.pawtopia.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes
    val icon: Int,
) {
    data object Home : BottomBarScreen(
        route = "HOME",
        title = "Beranda",
        icon = R.drawable.ic_home
    )

    data object Chat : BottomBarScreen(
        route = "CHAT",
        title = "Chat Bot",
        icon = R.drawable.ic_chat
    )

    data object Favorites : BottomBarScreen(
        route = "FAVORITES",
        title = "Favorit",
        icon = R.drawable.star_outline
    )

    data object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "Profil",
        icon = R.drawable.ic_person
    )


}