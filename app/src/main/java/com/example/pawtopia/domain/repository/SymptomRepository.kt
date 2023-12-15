package com.example.pawtopia.domain.repository

import com.example.pawtopia.common.state.Resource
import com.example.pawtopia.data.model.Symptom
import com.example.pawtopia.data.model.SymptomResponse
import kotlinx.coroutines.flow.Flow

interface SymptomRepository {
    suspend fun getSymptoms(): Flow<Resource<SymptomResponse>>
}