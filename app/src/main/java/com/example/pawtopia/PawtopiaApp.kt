package com.example.pawtopia

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.pawtopia.navigation.graphs.Graph
import com.example.pawtopia.navigation.graphs.RootNavigationGraph
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun PawtopiaApp(
    startDestination: String
) {
    Box(modifier = Modifier.fillMaxSize()
        .background(color = Color.White)) {
//        Image(
//            painter = painterResource(id = R.drawable.background),
//            contentDescription = "Background",
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.matchParentSize()
//        )
    }
    RootNavigationGraph(startDestination = startDestination)
}
