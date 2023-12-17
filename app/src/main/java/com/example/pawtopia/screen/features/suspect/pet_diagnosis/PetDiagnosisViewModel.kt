package com.example.pawtopia.screen.features.suspect.pet_diagnosis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pawtopia.common.state.Resource
import com.example.pawtopia.data.model.SymptomResponse
import com.example.pawtopia.domain.repository.PawtopiaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDiagnosisViewModel @Inject constructor(
    private val pawtopiaRepository: PawtopiaRepository
) : ViewModel() {

    private val _symptom: MutableStateFlow<Resource<SymptomResponse>> = MutableStateFlow(Resource.None)
    val symptom: StateFlow<Resource<SymptomResponse>> = _symptom

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
}