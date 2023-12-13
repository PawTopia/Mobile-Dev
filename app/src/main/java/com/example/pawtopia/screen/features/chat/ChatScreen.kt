package com.example.pawtopia.screen.features.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawtopia.common.component.TopBar
import com.example.pawtopia.data.model.Chat
import com.example.pawtopia.data.model.chatList

@Composable
fun ChatScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

    var message by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        LightGray, RoundedCornerShape(
                            topStart = 30.dp, topEnd = 30.dp
                        )
                    )
                    .padding(top = 25.dp)


            ) {
                LazyColumn(
                    modifier = Modifier.padding(
                        start = 15.dp,
                        end = 15.dp,
                        bottom = 75.dp
                    ),
                ) {
                    items(chatList, key = { it.id }) {
                        ChatRow(chat = it)
                    }
                }
            }
        }

        CustomTextField(
            text = message, onValueChange = { message = it },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .align(Alignment.BottomCenter)
        )
    }

}

@Composable
fun ChatRow(
    chat: Chat
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (chat.isMe) Alignment.Start else Alignment.End
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (chat.isMe) Yellow else White,
                    RoundedCornerShape(100.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = chat.message, style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                ),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                textAlign = TextAlign.End
            )
        }
        Text(
            text = chat.time,
            style = TextStyle(
                color = Gray,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
        )
    }

}

@Composable
fun CustomTextField(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text, onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = "Message",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            disabledContainerColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = { CommonIconButtonDrawable(Icons.Default.Send) },
        modifier = modifier.fillMaxWidth(),
        shape = CircleShape
    )

}


@Composable
fun CommonIconButtonDrawable(
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .background(Yellow, CircleShape)
            .size(33.dp), contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon, contentDescription = null,
            modifier = Modifier.size(15.dp)
        )
    }

}


