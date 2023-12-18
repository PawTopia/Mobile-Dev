package com.example.pawtopia.screen.features.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawtopia.common.util.DataDummy.dummyChat
import com.example.pawtopia.data.model.Chat

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
) {

    var message by remember { mutableStateOf("") }
    val chat = remember { mutableStateListOf<Chat>() }
    LaunchedEffect(true ) {
        chat.addAll(dummyChat)
    }


    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.padding(
                        start = 15.dp,
                        end = 15.dp,
                        bottom = 75.dp
                    ),
                ) {
                    item { Spacer(modifier = Modifier.height(10.dp)) }
                    items(chat) {
                        ChatRow(chat = it)
                    }
                }
            }
        }

        CustomTextField(
            text = message,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .align(Alignment.BottomCenter),
            onValueChange = { message = it },
            send = {
                if (message.isNotEmpty()) {
                    chat.add(
                        Chat(
                            id = chat.size + 1,
                            message = message,
                            time = "12.21 PM",
                            isMe = true
                        )
                    )
                    message = ""
                }
            }
        )
    }

}

@Composable
fun ChatRow(
    chat: Chat
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (chat.isMe) Alignment.End else Alignment.Start
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
    onValueChange: (String) -> Unit,
    send: () -> Unit
) {
    TextField(
        value = text, onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = "Message",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Gray
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
        trailingIcon = { CommonIconButtonDrawable(onClick = send) },
        modifier = modifier.fillMaxWidth(),
        shape = CircleShape
    )

}


@Composable
fun CommonIconButtonDrawable(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Yellow, CircleShape)
            .size(33.dp)
            .clickable { onClick() }, contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Send, contentDescription = null,
            modifier = Modifier.size(15.dp)
        )
    }

}


