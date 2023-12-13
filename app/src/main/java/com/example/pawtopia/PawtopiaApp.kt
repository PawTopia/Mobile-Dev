package com.example.pawtopia

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.pawtopia.navigation.graphs.Graph
import com.example.pawtopia.navigation.graphs.RootNavigationGraph
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun PawtopiaApp() {
    val user = Firebase.auth.currentUser
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_transparent),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
    }
    RootNavigationGraph(startDestination = if (user != null) Graph.MAIN else Graph.AUTHENTICATION)
}
