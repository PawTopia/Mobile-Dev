package com.example.pawtopia.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawtopia.R

@Composable
fun DoctorColumn(
    modifier: Modifier = Modifier
) {
    val columnState = rememberLazyListState()
    LazyColumn(
        state = columnState,
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(20) {
            DoctorCard()
        }
    }

}

@Composable
fun DoctorCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.foto_dokter),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "James Husk, D", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = "Testing Specialist", fontSize = 12.sp, fontWeight = FontWeight.Light)
            }
        }
    }
}

@Preview
@Composable
fun DoctorColumnPreview() {
    DoctorColumn()
}

@Preview(showBackground = true)
@Composable
fun DoctorCardPreview() {
    DoctorCard()
}