package com.example.pawtopia.screen.doctor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawtopia.common.component.DoctorCard
import com.example.pawtopia.common.component.DoctorColumn

@Composable
fun DoctorScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(20.dp,20.dp,20.dp,0.dp)){
        Header()
        DoctorColumn()
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedIconButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(30),
            colors = IconButtonDefaults.outlinedIconButtonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back Button")
        }
        Text(text = "Choose What The Best For You", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorScreenPreview() {
    DoctorScreen()
}