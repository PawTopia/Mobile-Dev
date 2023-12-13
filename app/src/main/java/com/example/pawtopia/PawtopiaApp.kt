package com.example.pawtopia

import androidx.compose.runtime.Composable
import com.example.pawtopia.navigation.graphs.Graph
import com.example.pawtopia.navigation.graphs.RootNavigationGraph
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun PawtopiaApp() {
    val user = Firebase.auth.currentUser
    RootNavigationGraph(startDestination = if (user != null) Graph.MAIN else Graph.AUTHENTICATION)
}
