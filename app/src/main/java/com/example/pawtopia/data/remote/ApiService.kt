package com.example.pawtopia.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("gejala")
    suspend fun getGejala()
}