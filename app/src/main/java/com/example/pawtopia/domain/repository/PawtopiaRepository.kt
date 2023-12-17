package com.example.pawtopia.domain.repository

import com.example.pawtopia.common.state.Resource
import com.example.pawtopia.data.model.Doctor
import com.example.pawtopia.data.model.SymptomResponse
import kotlinx.coroutines.flow.Flow

interface PawtopiaRepository {
    suspend fun getSymptoms(): Flow<Resource<SymptomResponse>>

    fun doctorById(doctorId: Int): Flow<Doctor>
}