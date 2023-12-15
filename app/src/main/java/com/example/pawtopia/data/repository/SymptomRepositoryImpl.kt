package com.example.pawtopia.data.repository

import com.example.pawtopia.common.state.Resource
import com.example.pawtopia.data.model.Response
import com.example.pawtopia.data.model.Symptom
import com.example.pawtopia.data.model.SymptomResponse
import com.example.pawtopia.data.remote.ApiService
import com.example.pawtopia.domain.repository.SymptomRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class SymptomRepositoryImpl(
    private val apiService: ApiService
): SymptomRepository {

    override suspend fun getSymptoms(): Flow<Resource<SymptomResponse>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.getSymptons()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            if (e is HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, Response::class.java)
                emit(Resource.Error(errorBody.message))
            } else {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

}