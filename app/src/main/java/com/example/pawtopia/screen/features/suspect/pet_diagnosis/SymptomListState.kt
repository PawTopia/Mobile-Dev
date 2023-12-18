package com.example.pawtopia.screen.features.suspect.pet_diagnosis

import com.example.pawtopia.data.model.Symptom

data class SymptomListState(
    val isLoading: Boolean = false,
    val symptomps: List<Symptom> = emptyList(),
    val error: String = ""
)
