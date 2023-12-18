package com.example.pawtopia.data.model

import com.google.gson.annotations.SerializedName

data class SymptomResponse(
    val success: Boolean,
    val message: String,
    val data: List<Symptom>

)

data class Symptom(
    val id: Int,
    @field:SerializedName("nama")
    val name: String,
    val isSelected: Boolean = false,
)

data class PredictResponse(
    val prediction: String,
    val data: PredictData,
    val gejala: String,
    val message: String,
)

data class PredictData(
    val description: String,
    val treatment: String,
)