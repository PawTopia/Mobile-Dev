package com.example.pawtopia.screen.features.clinic

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.PinDrop
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawtopia.R
import com.example.pawtopia.common.component.RatingBar
import com.example.pawtopia.common.component.TopBar

@Composable
fun DetailClinicScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Nama Klinik", style = MaterialTheme.typography.titleLarge)
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Outlined.BookmarkAdd, contentDescription = "Bookmark")
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RatingBar(rating = 4.7)
                Text(text = "4.7", fontSize = 16.sp)
            }
            Row {
                Icon(imageVector = Icons.Outlined.PinDrop, contentDescription = null)
                Text(text = "Jl. Alamat Klinik No 999, Kecamatan Alamat", fontSize = 16.sp)
            }
            Text(text = "Deskripsi Klinik", style = MaterialTheme.typography.titleMedium)
            Text(text = LoremIpsum().values.first().take(120))
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "See Maps")
            }
        }

    }
}

