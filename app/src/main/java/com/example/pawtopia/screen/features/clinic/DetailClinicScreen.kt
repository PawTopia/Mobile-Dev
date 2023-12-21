package com.example.pawtopia.screen.features.clinic

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pawtopia.R
import com.example.pawtopia.common.component.RatingBar

@Composable
fun DetailClinicScreen(
    clinicId: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailClinicViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    viewModel.getClinicById(clinicId)
    val result by viewModel.result.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.klinik_hewan),
            contentDescription = "Gambar Klinik",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = result.name, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                IconButton(onClick = {}) {
                    Icon(painter = painterResource(id = R.drawable.bookmark_add), contentDescription = "Bookmark")
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RatingBar(rating = result.rating)
                Text(text = "4.7", fontSize = 18.sp)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.place), contentDescription = null)
                Text(text = result.address, fontSize = 18.sp)
            }
            Text(text = "Deskripsi Klinik", style = MaterialTheme.typography.titleMedium)
            Text(text = result.desc, textAlign = TextAlign.Justify)
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    Toast.makeText(
                    context,
                    "Fitur Belum diimplementasikan",
                    Toast.LENGTH_SHORT
                ).show() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Lihat di Google Maps")
            }
        }

    }
}

