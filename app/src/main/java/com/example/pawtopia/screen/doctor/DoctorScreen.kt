package com.example.pawtopia.screen.doctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawtopia.R
import com.example.pawtopia.common.component.TopBar

@Composable
fun DoctorScreen(
    navigateToDetailDoctor: () -> Unit,
    navigateUp: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp)
    ) {
        TopBar(
            title = "Choose What The Best For You",
            onClick = navigateUp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        DoctorColumn(navigateToDetailDoctor = navigateToDetailDoctor)
    }
}

@Composable
fun DoctorColumn(
    navigateToDetailDoctor: () -> Unit,
    modifier: Modifier = Modifier
) {
    val columnState = rememberLazyListState()
    LazyColumn(
        state = columnState,
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(20) {
            DoctorCard(onClick = navigateToDetailDoctor)
        }
    }
}

@Composable
fun DoctorCard(
    onClick: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.foto_dokter),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(text = "James Husk, D", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = "Testing Specialist", fontSize = 16.sp, fontWeight = FontWeight.Light)
            }
        }
    }
}
