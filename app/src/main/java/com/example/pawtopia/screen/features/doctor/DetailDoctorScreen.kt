package com.example.pawtopia.screen.features.doctor

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pawtopia.R
import com.example.pawtopia.common.component.RatingBar

@Composable
fun DetailDoctorScreen(
    doctorId: Int,
    navigateToConversation: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailDoctorViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    viewModel.getDoctorById(doctorId)
    val result by viewModel.result.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        DoctorProfileCard(
            name = result.name,
            jobTitle = result.jobTitle,
            desc = result.desc
        )
        Spacer(modifier = Modifier.height(10.dp))
        ElevatedButton(
            onClick = { navigateToConversation(result.name) },
            modifier = Modifier.fillMaxWidth(0.7f),
            shape = RoundedCornerShape(25),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Hubungi dokter")
        }
        ElevatedButton(
            onClick = {
                Toast.makeText(context, "Fitur Belum diimplementasikan", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(0.7f),
            shape = RoundedCornerShape(25),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            border = BorderStroke(
                color = MaterialTheme.colorScheme.primary,
                width = 2.dp
            )
        ) {
            Text(text = "Reservasi")
        }
    }

}

@Composable
fun DoctorProfileCard(
    name: String,
    jobTitle: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            2.dp
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
                    DoctorJobname(
                        name = name,
                        jobTitle = jobTitle
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RatingCard(title = "Rating", value = "4.7", iconCard = {
                            RatingBar(
                                modifier = Modifier.padding(4.dp),
                                rating = 3.5
                            )
                        })
                        RatingCard(title = "Patients", value = "500+", iconCard = {
                            Icon(painterResource(id = R.drawable.groups), contentDescription = null)
                        })
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "About", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(text = desc)
        }
    }
}

@Composable
fun DoctorJobname(
    name: String,
    jobTitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = name, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        Text(
            text = jobTitle,
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
    iconCard: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(60.dp)
            .width(90.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = value)
            iconCard()
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}
