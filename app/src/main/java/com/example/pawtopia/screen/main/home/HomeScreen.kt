package com.example.pawtopia.screen.main.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pawtopia.R
import com.example.pawtopia.common.util.DataDummy.dummyClinic
import com.example.pawtopia.common.util.fillWidthOfParent
import com.example.pawtopia.data.model.Clinic
import com.example.pawtopia.ui.theme.consultColor
import com.example.pawtopia.ui.theme.diagnoseColor
import com.example.pawtopia.ui.theme.onConsultColor
import com.example.pawtopia.ui.theme.onDiagnoseColor
import com.example.pawtopia.ui.theme.onRecommendColor
import com.example.pawtopia.ui.theme.recommendColor

@Composable
fun HomeScreen(
    navigateToFindDoctor: () -> Unit,
    navigateToFindClinic: () -> Unit,
    navigateToSuspect: () -> Unit,
    navigateToDetailClinic: (String, Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val displayName = viewModel.userDisplayName.toString()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 0.dp)
        ) {
            ProfileCard(name = displayName)
            Spacer(modifier = Modifier.height(12.dp))
            Divider(
                modifier = Modifier.fillWidthOfParent(24.dp),
                thickness = 12.dp, color = MaterialTheme.colorScheme.primary
            )
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.height(8.dp))
                Card {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "Services", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        FeatureButton(
                            image = R.drawable.consultancy,
                            title = "Konsultasi dengan Dokter",
                            containerColor = consultColor,
                            imageColor = onConsultColor,
                            onClick = navigateToFindDoctor
                        )
                        FeatureButton(
                            image = R.drawable.diagnose_and_treatment,
                            title = "Suspect Penyakit",
                            containerColor = diagnoseColor,
                            imageColor = onDiagnoseColor,
                            onClick = navigateToSuspect
                        )
                        FeatureButton(
                            image = R.drawable.recommendation_clinic,
                            title = "Rekomendasi Klinik Hewan",
                            containerColor = recommendColor,
                            imageColor = onRecommendColor,
                            onClick = navigateToFindClinic
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Card {
                    Column(modifier = Modifier.padding(12.dp, 8.dp, 12.dp, 0.dp)) {
                        Text(text = "Pet Care Nearby You", fontWeight = FontWeight.Bold)
                        ClinicsRow(clinicList = dummyClinic, onClick = navigateToDetailClinic)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    name: String? = "Ini Placeholder",
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(15))
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.photo_profile4x),
                    contentDescription = "profil foto",
                    modifier = Modifier.padding(12.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(text = "Hi, $name!")
                Text(
                    text = "Medan Kota, Medan",
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            OutlinedIconButton(
                onClick = { /*TODO*/ },
                border = BorderStroke(Dp.Hairline, Color.Gray),
                shape = RoundedCornerShape(30)
            ) {
                Icon(Icons.Outlined.Notifications, contentDescription = "Localized description")
            }
        }
    }
}

@Composable
fun FeatureButton(
    @DrawableRes image: Int,
    title: String,
    containerColor: Color,
    imageColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(image),
                contentDescription = title,
                colorFilter = ColorFilter.tint(color = imageColor)
            )
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun ClinicsRow(
    clinicList: List<Clinic>,
    onClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val rowState = rememberLazyListState()
    LazyRow(
        state = rowState,
        modifier = modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(clinicList) { clinic ->
            ClinicHomeItem(
                name = clinic.name,
                photoUrl = clinic.photoUrl,
                onClick = { onClick(clinic.name, clinic.id) }
            )
        }
    }
}

@Composable
fun ClinicHomeItem(
    name: String,
    photoUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(topStartPercent = 15, topEndPercent = 15),
        border = BorderStroke(width = 1.dp, color = Color.Gray)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(id = R.drawable.klinik_hewan),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = name,
                modifier = Modifier.padding(start = 4.dp),
                fontWeight = FontWeight.Medium
            )
        }
    }
}
