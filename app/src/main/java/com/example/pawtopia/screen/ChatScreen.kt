package com.example.pawtopia.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawtopia.R

@Composable
fun ChatScreen() {
    Column(modifier = Modifier.padding(20.dp,20.dp,20.dp,0.dp)) {
        ChatListHeader(modifier = Modifier.fillMaxWidth())
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .align(Alignment.End), contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp), shape = MaterialTheme.shapes.medium) {
            Text(text = "New Chat")
            Spacer(modifier = Modifier.width(4.dp))
            Icon(imageVector = Icons.Default.Add, contentDescription = "New Chat Button")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(15) {
                ChatListCard(titleMessage = "Doctor Consultation", lastMessage = LoremIpsum().values.first().take(90))
            }
        }
    }
}

@Composable
fun ChatListHeader(modifier: Modifier = Modifier) {
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
        Text(text = "Pawtopia Assistance", fontSize = 24.sp, fontWeight = FontWeight.Bold )
    }
}

@Composable
fun ChatListCard(
    titleMessage: String,
    lastMessage: String
) {
    Card {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_chat),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = titleMessage, fontWeight = FontWeight.Bold)
                Text(
                    text = lastMessage,
                    fontWeight = FontWeight.Light,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true,
                    maxLines = 2
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}

@Preview
@Composable
fun ChatListCardPreview() {
    ChatListCard(
        titleMessage = "My pet is difficult to handle. How....?",
        lastMessage = LoremIpsum().values.first().take(90)
    )
}

@Preview(showBackground = true)
@Composable
fun ChatListHeaderPreview() {
    ChatListHeader()
}