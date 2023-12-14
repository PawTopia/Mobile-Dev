package com.example.pawtopia.screen.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pawtopia.common.component.BottomBar
import com.example.pawtopia.navigation.BottomBarScreen
import com.example.pawtopia.navigation.FeaturesScreen
import com.example.pawtopia.navigation.graphs.MainNavGraph

@Composable
fun MainScreen(
    navigateToAuth: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var title by rememberSaveable {
        mutableStateOf("")
    }

    val bottomBarItem = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Chat,
        BottomBarScreen.Favorites,
        BottomBarScreen.Profile,
    )

    val topBarItem = listOf(
        BottomBarScreen.Home.route,
        BottomBarScreen.Chat.route,
        BottomBarScreen.Favorites.route,
        BottomBarScreen.Profile.route,
        FeaturesScreen.FindClinic.route
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val bottomBarDestination = bottomBarItem.any { it.route == currentRoute }

    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                navController = navController,
                topBarDestination = currentRoute !in topBarItem,
                modifier = modifier.shadow(elevation = 1.dp)
            )
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                bottomBarDestination = bottomBarDestination,
                currentDestination = currentDestination,
                items = bottomBarItem
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            MainNavGraph(
                onFeaturesTitleChanged = {
                    title = it
                },
                navigateToAuth = navigateToAuth, navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    navController: NavHostController,
    topBarDestination: Boolean,
    modifier: Modifier = Modifier
) {
    if (topBarDestination) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            },
            modifier = modifier.padding(20.dp, 10.dp),
            navigationIcon = {
                OutlinedIconButton(
                    onClick = { navController.navigateUp() },
                    shape = RoundedCornerShape(30),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back Button"
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent
            )
        )
    }

}