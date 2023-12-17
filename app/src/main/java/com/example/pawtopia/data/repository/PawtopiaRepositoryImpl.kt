package com.example.pawtopia.data.repository

import com.example.pawtopia.common.state.Resource
import com.example.pawtopia.common.util.DataDummy
import com.example.pawtopia.data.model.Clinic
import com.example.pawtopia.data.model.Doctor
import com.example.pawtopia.data.model.PredictResponse
import com.example.pawtopia.data.model.Response
import com.example.pawtopia.data.model.SymptomResponse
import com.example.pawtopia.data.remote.SuspectApi
import com.example.pawtopia.data.remote.SymptomApi
import com.example.pawtopia.domain.repository.PawtopiaRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PawtopiaRepositoryImpl(
    private val symptomApi: SymptomApi,
    private val suspectApi: SuspectApi
): PawtopiaRepository {

    override suspend fun getSymptoms(): Flow<Resource<SymptomResponse>> = flow {
        emit(Resource.Loading)
        try {
            val response = symptomApi.getSymptons()
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

    override suspend fun postSymptom(gejala: String): Flow<Resource<PredictResponse>> = flow {
        emit(Resource.Loading)
        try {
            val response = suspectApi.postSymptom(gejala)
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

    override fun doctorById(doctorId: Int): Flow<Doctor> = flow {
        DataDummy.dummyDoctor.find { it.id == doctorId }?.let { emit(it) }
    }

    override fun clinicById(clinicId: Int): Flow<Clinic> = flow {
        DataDummy.dummyClinic.find { it.id == clinicId }?.let { emit(it) }
    }

}