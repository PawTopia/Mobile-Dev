package com.example.pawtopia.common.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.pawtopia.navigation.BottomBarScreen

@Composable
fun BottomBar(
    navController: NavHostController,
    bottomBarDestination: Boolean,
    currentDestination: NavDestination?,
    items: List<BottomBarScreen>,
    modifier: Modifier = Modifier
) {

    if (bottomBarDestination) {
        NavigationBar(modifier = modifier) {
            items.forEach { item ->
                val selected = currentDestination?.route == item.route
                NavigationBarItem(
                    label = {
                        Text(
                            text = item.title,
                            color = if (selected) Color.Red else Color.Gray
                        )
                    },
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = if (selected) Color.Red else Color.Gray
                        )
                    })
            }
        }
    }

}