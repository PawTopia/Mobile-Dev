package com.example.pawtopia.data.remote

import com.example.pawtopia.data.model.SymptomResponse
import retrofit2.http.GET

interface SymptomApi {
    @GET("gejala")
    suspend fun getSymptons() : SymptomResponse
}