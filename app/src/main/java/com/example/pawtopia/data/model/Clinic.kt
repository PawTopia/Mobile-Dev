package com.example.pawtopia.data.model

data class Clinic(
    val name: String,
    val desc: String,
    val photoUrl: String,
    val lat: Double,
    val lon: Double
)

// Dummy data
val clinicList = listOf(
    Clinic(
        name = "Clinic 1",
        desc = "lorem ipsum dolor sit amet 1",
        photoUrl = "",
        lat = 6.88,
        lon = 109.67
    ),
    Clinic(
        name = "Clinic 2",
        desc = "lorem ipsum dolor sit amet 2",
        photoUrl = "",
        lat = 6.88,
        lon = 109.67
    ),
    Clinic(
        name = "Clinic 3",
        desc = "lorem ipsum dolor sit amet 3",
        photoUrl = "",
        lat = 6.88,
        lon = 109.67
    ),
    Clinic(
        name = "Clinic 4",
        desc = "lorem ipsum dolor sit amet 4",
        photoUrl = "",
        lat = 6.88,
        lon = 109.67
    ),
    Clinic(
        name = "Clinic 5",
        desc = "lorem ipsum dolor sit amet 5",
        photoUrl = "",
        lat = 6.88,
        lon = 109.67
    )
)