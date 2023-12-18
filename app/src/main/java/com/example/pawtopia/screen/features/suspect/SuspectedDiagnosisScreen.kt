package com.example.pawtopia.screen.features.suspect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.pawtopia.ui.theme.primaryColor
import com.example.pawtopia.ui.theme.suggestButtonConsultColor
import com.example.pawtopia.ui.theme.suggestButtonRecommendColor
import com.example.pawtopia.ui.theme.suggestConsultColor
import com.example.pawtopia.ui.theme.suggestRecommendColor

@Composable
fun SuspectedDiagnosisScreen(
    prediction: String,
    description: String,
    treatment: String,
    navigateToFindClinic: () -> Unit,
    navigateToFindDoctor: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = prediction,
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        color = primaryColor,
                        cornerRadius = CornerRadius(x = 30f, y = 30f)
                    )
                }
                .padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold)
        )
        Card {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
            ) {
                Text(text = "Deskripsi", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = description
                )
            }
        }

        Card {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)

            ) {
                Text(text = "Perawatan", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = treatment
                )
            }
        }

        Card {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
            ) {
                Text(text = "Saran tindakan", style = MaterialTheme.typography.titleMedium)
                SuggestCard(
                    desc = "Get advice from veterinarians tailored to your pet's health needs.",
                    textButton = "Konsultasi dengan Dokter",
                    cardColor = suggestConsultColor,
                    onClick = { navigateToFindDoctor() },
                    buttonColor = suggestButtonConsultColor,
                )
                SuggestCard(
                    desc = "Find the nearest veterinary clinic for prompt medical attention for your pet.",
                    textButton = "Rekomendasi Klinik Hewan",
                    cardColor = suggestRecommendColor,
                    onClick = { navigateToFindClinic() },
                    buttonColor = suggestButtonRecommendColor,
                )
            }
        }
        Text(
            text = "Kembali ke halaman beranda",
            modifier = Modifier.clickable { navigateToHome() },
            color = Color.Red,
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun SuggestCard(
    desc: String,
    textButton: String,
    cardColor: Color,
    onClick: () -> Unit,
    buttonColor: Color,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)
        ) {
            Text(text = desc)
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(vertical = 0.dp, horizontal = 12.dp)
            ) {
                Text(text = textButton)
            }
        }
    }
}
