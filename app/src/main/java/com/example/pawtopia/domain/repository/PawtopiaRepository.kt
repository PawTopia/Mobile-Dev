package com.example.pawtopia.domain.repository

import com.example.pawtopia.common.state.Resource
import com.example.pawtopia.data.model.Clinic
import com.example.pawtopia.data.model.Doctor
import com.example.pawtopia.data.model.PredictResponse
import com.example.pawtopia.data.model.SymptomResponse
import kotlinx.coroutines.flow.Flow

interface PawtopiaRepository {
    suspend fun getSymptoms(): Flow<Resource<SymptomResponse>>

    suspend fun postSymptom(
        gejala: String
    ): Flow<Resource<PredictResponse>>

    fun doctorById(doctorId: Int): Flow<Doctor>

    fun clinicById(clinicId: Int): Flow<Clinic>
}