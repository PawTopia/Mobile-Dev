package com.example.pawtopia.data.model

data class Clinic(
    val id: Int,
    val name: String,
    val address: String,
    val desc: String,
    val rating: Double,
    val photoUrl: String,
    val lat: Double,
    val lon: Double
)

