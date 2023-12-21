package com.example.pawtopia.screen.features.doctor

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pawtopia.R

@Composable
fun DoctorProfileScreen(
    doctorId: Int,
    navigateToConversation: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailDoctorViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    viewModel.getDoctorById(doctorId)
    val result by viewModel.result.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.foto_dokter),
                contentDescription = "Foto Dokter"
            )
            Box(
                modifier = Modifier.background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        border = BorderStroke(Dp.Hairline, MaterialTheme.colorScheme.outline)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = result.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Text(text = result.jobTitle)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        DoctorStatus()
                        DoctorStatus()
                        DoctorStatus()
                        DoctorStatus()
                        DoctorStatus()
                    }
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "About", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text(text = result.desc)
                    }
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
                            Toast.makeText(
                                context,
                                "Fitur Belum diimplementasikan",
                                Toast.LENGTH_SHORT
                            ).show()
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
        }
    }
}


@Composable
fun DoctorStatus(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color.Red)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "116+", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Patients")
    }
}


@Preview(
    showBackground = true,
)
@Composable
fun DoctorStatusPreview() {
    DoctorStatus()
}

@Preview(showBackground = true)
@Composable
fun DoctorProfilePreview() {
    DoctorProfileScreen(1, {})
}