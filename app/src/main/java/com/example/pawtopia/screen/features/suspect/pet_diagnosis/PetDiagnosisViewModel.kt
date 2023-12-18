package com.example.pawtopia.screen.features.suspect.pet_diagnosis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pawtopia.common.state.Resource
import com.example.pawtopia.common.util.Event
import com.example.pawtopia.data.model.PredictResponse
import com.example.pawtopia.data.model.SymptomResponse
import com.example.pawtopia.domain.repository.PawtopiaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDiagnosisViewModel @Inject constructor(
    private val pawtopiaRepository: PawtopiaRepository
) : ViewModel() {

    private val _symptom: MutableStateFlow<Resource<SymptomResponse>> =
        MutableStateFlow(Resource.None)
    val symptom: StateFlow<Resource<SymptomResponse>> = _symptom

    private val _predict: MutableStateFlow<Event<Resource<PredictResponse>>> = MutableStateFlow(Event(Resource.None))
    val predict: StateFlow<Event<Resource<PredictResponse>>> = _predict

    init {
        getSymptoms()
    }

    private fun getSymptoms() {
        viewModelScope.launch {
            pawtopiaRepository.getSymptoms().collect {
                _symptom.value = it
            }
        }
    }

    fun postSymptom(
        gejala: String
    ) {
        viewModelScope.launch {
            pawtopiaRepository.postSymptom(gejala).collect {
                _predict.value = Event(it)
            }
        }
    }
}