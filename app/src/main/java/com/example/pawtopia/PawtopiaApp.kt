package com.example.pawtopia

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pawtopia.navigation.NavigationItem
import com.example.pawtopia.navigation.Screen
import com.example.pawtopia.screen.ChatScreen
import com.example.pawtopia.screen.FavoriteScreen
import com.example.pawtopia.screen.HomeScreen
import com.example.pawtopia.screen.LoginScreen
import com.example.pawtopia.screen.ProfileScreen
import com.example.pawtopia.screen.RegisterScreen
import com.example.pawtopia.screen.WelcomeScreen

@Composable
fun PawtopiaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
//                 when(currentRoute) {
//                     Screen.Home.route ->
//                     Screen.Home.route ->
//                 }
        },
        bottomBar = {
            if (currentRoute != Screen.Welcome.route) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        val context = LocalContext.current
        NavHost(
            navController = navController,
            startDestination = Screen.Welcome.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Welcome.route) {
                WelcomeScreen(navigateToHome = {
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                },
                    onNavUp = {
                        val navUp = navController.navigateUp()
                        if (!navUp) (context as Activity).finish()
                    }
                )
            }
            composable(Screen.Login.route) {
                LoginScreen(navigateToRegister = {
                    navController.popBackStack()
                    navController.navigate(Screen.Register.route)
                },
                    login = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)
                    }
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(navigateToLogin = {
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                },
                    register = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Chat.route) {
                ChatScreen()
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Chat",
                icon = ImageVector.vectorResource(R.drawable.ic_chat),
                screen = Screen.Chat
            ),
            NavigationItem(
                title = "Favorite",
                icon = ImageVector.vectorResource(R.drawable.ic_favorite),
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = "Profile",
                icon = Icons.Default.Person,
                screen = Screen.Profile
            ),
        )

        navigationItem.map { item ->
            NavigationBarItem(
                label = { Text(text = item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                })
        }
    }
}