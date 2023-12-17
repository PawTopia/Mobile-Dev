package com.example.pawtopia.common.util

import com.example.pawtopia.data.model.Chat
import com.example.pawtopia.data.model.ChatHistory
import com.example.pawtopia.data.model.Clinic
import com.example.pawtopia.data.model.Doctor

object DataDummy {

    val dummyDoctor: List<Doctor> by lazy {
        (1..20).map { index ->
            Doctor(
                id = index,
                name = "Dokter $index",
                jobTitle = "Testing Specialist $index",
                desc = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. $index",
                photoUrl = "",
            )
        }
    }

    val dummyClinic: List<Clinic> by lazy {
        (1..20).map { index ->
            Clinic(
                id= index,
                name = "Clinic $index",
                desc = "lorem ipsum dolor sit amet $index",
                rating = 4.5,
                photoUrl = "",
                lat = 6.88,
                lon = 109.67
            )
        }
    }

    val dummyChatHistory: List<ChatHistory> by lazy {
        (1..20).map { index ->
            ChatHistory(
                doctorName = "Dokter $index",
                message = "lorem ipsum dolor sit amet $index",
            )
        }
    }

    val dummyChat = listOf(
        Chat(
            1,
            "Hey! How have you been?",
            "12:15 PM",
            true
        ),
        Chat(
            2,
            "Wanna catch up for a beer?",
            "12:17 PM",
            true
        ),
        Chat(
            3,
            "Awesome! Let’s meet up",
            "12:19 PM",
            false
        ),
        Chat(
            4,
            "Can I also get my cousin along? Will that be okay?",
            "12:20 PM",
            false
        ),
        Chat(
            5,
            "Yeah sure! get him too.",
            "12:21 PM",
            true
        ),
        Chat(
            6,
            "Hey! How have you been?",
            "12:15 PM",
            false
        ),
        Chat(
            7,
            "Wanna catch up for a beer?",
            "12:17 PM",
            true
        ),
        Chat(
            8,
            "Awesome! Let’s meet up",
            "12:19 PM",
            false
        ),
        Chat(
            9,
            "Can I also get my cousin along? Will that be okay?",
            "12:20 PM",
            false
        ),
        Chat(
            10,
            "Yeah sure! get him too.",
            "12:21 PM",
            true
        ),
    )

}