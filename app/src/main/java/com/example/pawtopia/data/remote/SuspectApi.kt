package com.example.pawtopia.data.remote

import com.example.pawtopia.data.model.PredictResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SuspectApi {
    @FormUrlEncoded
    @POST("predict")
    suspend fun postSymptom(
        @Field("gejala") gejala: String
    ) : PredictResponse

}