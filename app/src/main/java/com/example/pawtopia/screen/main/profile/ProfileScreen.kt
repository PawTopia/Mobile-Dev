package com.example.pawtopia.screen.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.sp
import com.example.pawtopia.R
import com.example.pawtopia.common.component.TopBar
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(20.dp)
        ) {
            TopBar(
                title = "Akun Profil", modifier = Modifier.padding(bottom = 12.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.foto_dokter),
                contentDescription = "profil foto",
                modifier = Modifier
                    .size(150.dp)
                    .background(color = Color.LightGray, shape = CircleShape)
                    .clip(CircleShape)
            )
            Text(
                text = "John Delulu",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 40.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Informasi pribadi", style = MaterialTheme.typography.headlineSmall)
            TextFieldCard(label = "Nama", value = "John Delulu")
            TextFieldCard(label = "Nomor Telepon", value = "+62 85 1500 21000")
            TextFieldCard(label = "Tanggal Lahir", value = "DD MM YYYY")
            TextFieldCard(label = "Domisili", value = "Medan")

        }
        ElevatedButton(
            onClick = {
                FirebaseAuth.getInstance().signOut()
                navigateToLogin()
            },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Log Out", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(12.dp))
                Icon(imageVector = Icons.Rounded.Logout, contentDescription = "Logout Button")
            }
        }
    }
}

@Composable
fun TextFieldCard(
    label: String,
    value: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(navigateToLogin = { /*TODO*/ })
}
