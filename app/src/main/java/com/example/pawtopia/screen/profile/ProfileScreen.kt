package com.example.pawtopia.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pawtopia.R
import com.example.pawtopia.common.component.TopBar
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)) {
        TopBar(title = "Profile", onClick = navigateUp, modifier = Modifier.padding(bottom = 12.dp))
        Image(
            painter = painterResource(id = R.drawable.foto_dokter),
            contentDescription = "profil foto",
            modifier = Modifier
                .size(250.dp)
                .background(color = Color.LightGray, shape = CircleShape)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "John Delulu",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(text = "JohnDelulu@test.com", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Medan, Medan Kota", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = {
            FirebaseAuth.getInstance().signOut()
            navigateToLogin()
        }) {
            Text(text = "Log out")
        }
    }
}
