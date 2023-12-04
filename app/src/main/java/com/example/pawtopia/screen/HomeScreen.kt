package com.example.pawtopia.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawtopia.R
import com.example.pawtopia.common.fillWidthOfParent

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            ProfileCard()
            Spacer(modifier = Modifier.height(12.dp))
            Divider(
                modifier = Modifier.fillWidthOfParent(24.dp),
                thickness = 12.dp, color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FeatureButton(image = R.drawable.consultation, title = "Consultansy With Doctor1", containerColor = MaterialTheme.colorScheme.primary, imageColor = MaterialTheme.colorScheme.onPrimaryContainer)
                    FeatureButton(image = R.drawable.consultation, title = "Consultansy With Doctor2", containerColor = MaterialTheme.colorScheme.primary, imageColor = MaterialTheme.colorScheme.onPrimaryContainer)
                    FeatureButton(image = R.drawable.consultation, title = "Consultansy With Doctor3", containerColor = MaterialTheme.colorScheme.primary, imageColor = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
        }
    }
}

@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    ElevatedCard {
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
                Text(text = "Hi, Doe Doe!")
                Text(
                    text = "Medan Kota, Medan",
                    color = Color.LightGray,
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
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
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
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
@Preview(showBackground = true)
fun ProfileCardPreview() {
    FeatureButton(image = R.drawable.consultation, title = "Consultansy With Doctor", containerColor = MaterialTheme.colorScheme.primary, imageColor = MaterialTheme.colorScheme.onPrimaryContainer)
}