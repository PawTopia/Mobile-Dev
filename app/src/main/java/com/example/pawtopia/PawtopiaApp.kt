package com.example.pawtopia

import android.app.Activity
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
import com.example.pawtopia.screen.HomeScreen
import com.example.pawtopia.screen.SavedScreen
import com.example.pawtopia.screen.WelcomeScreen
import com.example.pawtopia.screen.chat.ChatListScreen
import com.example.pawtopia.screen.chat.ChatScreen
import com.example.pawtopia.screen.clinic.DetailClinicScreen
import com.example.pawtopia.screen.clinic.FindClinicScreen
import com.example.pawtopia.screen.doctor.DoctorDetailScreen
import com.example.pawtopia.screen.doctor.DoctorScreen
import com.example.pawtopia.screen.login.LoginScreen
import com.example.pawtopia.screen.profile.ProfileScreen
import com.example.pawtopia.screen.register.RegisterScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun PawtopiaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val user = Firebase.auth.currentUser

    Scaffold(
        bottomBar = {
            if (currentRoute !in arrayOf(
                    Screen.Welcome.route,
                    Screen.Login.route,
                    Screen.Register.route,
                    Screen.Chat.route
                )
            ) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        val context = LocalContext.current

        NavHost(
            navController = navController,
            startDestination = if (user != null) Screen.Home.route else Screen.Welcome.route,
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
                HomeScreen(
                    navigateToDoctor = {
                        navController.popBackStack()
                        navController.navigate(Screen.Saved.route)
                    },
                    navigateToDetailClinic = {
                        navController.popBackStack()
                        navController.navigate(Screen.FindClinic.route)
                    },
                    navigateToFindClinic = {
                        navController.popBackStack()
                        navController.navigate(Screen.FindClinic.route)
                    },
                    navigateToSuspect = {

                    }
                )
            }
            composable(Screen.ChatList.route) {
                ChatListScreen(
                    navigateToChat = {
                        navController.popBackStack()
                        navController.navigate(Screen.Chat.route)
                    },
                    navigateUp = { navController.navigateUp() }
                )
            }
            composable(Screen.Chat.route) {
                ChatScreen(navigateUp = {
                    navController.navigateUp()
                })
            }
            composable(Screen.Saved.route) {
                SavedScreen(
                    navigateToDetailClinic = {
                        navController.popBackStack()
                        navController.navigate(Screen.DetailClinic.route)
                    },
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.FindClinic.route) {
                FindClinicScreen(navigateToDetailClinic = {
                    navController.popBackStack()
                    navController.navigate(Screen.DetailClinic.route)
                },
                    navigateUp = {
                        navController.navigateUp()
                    })
            }
            composable(Screen.DetailClinic.route) {
                DetailClinicScreen(navigateUp = {
                    navController.navigateUp()
                })
            }
            composable(Screen.Doctor.route) {
                DoctorScreen(
                   navigateToDetailDoctor =  {
                        navController.popBackStack()
                        navController.navigate(Screen.DoctorProfile.route)
                    },
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.DoctorProfile.route) {
                DoctorDetailScreen(
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navigateToLogin = {
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
                    },
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
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
                screen = Screen.ChatList
            ),
            NavigationItem(
                title = "Favorite",
                icon = ImageVector.vectorResource(R.drawable.ic_favorite),
                screen = Screen.Saved
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
                alwaysShowLabel = false,
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

