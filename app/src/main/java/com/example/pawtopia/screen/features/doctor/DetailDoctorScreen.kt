package com.example.pawtopia.screen.features.doctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawtopia.R
import com.example.pawtopia.common.component.TopBar

@Composable
fun DetailDoctorScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        DoctorProfileCard()
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Contact them")
        }
    }

}

@Composable
fun DoctorProfileCard(
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            4.dp
        ),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(20.dp, 12.dp, 12.dp, 24.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(IntrinsicSize.Min)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.foto_dokter),
                    contentDescription = null,
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = MaterialTheme.shapes.small)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    DoctorJobname()
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RatingCard(title = "Rating", value = "4.7")
                        RatingCard(title = "Patients", value = "500+")
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "About", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(text = LoremIpsum().values.first().take(120), color = Color.Gray)
        }
    }
}

@Composable
fun DoctorJobname(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "James Husk, D", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        Text(
            text = "Testing Specialist",
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = Color.Gray
        )
    }
}

@Composable
fun RatingCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(60.dp)
            .width(90.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = value)
        }
    }
}
