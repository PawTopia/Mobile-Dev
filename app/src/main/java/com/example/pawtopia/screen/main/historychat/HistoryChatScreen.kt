package com.example.pawtopia.screen.main.historychat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.pawtopia.R
import com.example.pawtopia.common.component.TopBar

@Composable
fun HistoryChatScreen(
    navigateToChat: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(20.dp, 20.dp, 20.dp, 0.dp)) {
        TopBar(
            title = "Chat History",
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
        Button(
            onClick = { navigateToChat() },
            modifier = Modifier
                .align(Alignment.End),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "New Chat")
            Spacer(modifier = Modifier.width(4.dp))
            Icon(imageVector = Icons.Default.Add, contentDescription = "New Chat Button")
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(15) {
                ChatListCard(
                    titleMessage = "Doctor Consultation",
                    lastMessage = LoremIpsum().values.first().take(90),
                    onClick = navigateToChat
                )
            }
        }
    }
}


@Composable
fun ChatListCard(
    titleMessage: String,
    lastMessage: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.clickable { onClick() }
    ) {
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


