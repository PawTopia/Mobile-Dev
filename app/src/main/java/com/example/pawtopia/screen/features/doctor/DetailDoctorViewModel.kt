package com.example.pawtopia.screen.features.doctor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pawtopia.common.util.DataDummy
import com.example.pawtopia.data.model.Doctor
import com.example.pawtopia.domain.repository.PawtopiaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailDoctorViewModel @Inject constructor(
    private val pawtopiaRepository: PawtopiaRepository
) : ViewModel() {
    private val _result: MutableStateFlow<Doctor> = MutableStateFlow(DataDummy.dummyDoctor.first())
    val result: StateFlow<Doctor> = _result

    fun getDoctorById(doctorId: Int) {
        viewModelScope.launch {
            pawtopiaRepository.doctorById(doctorId).collect {
                _result.value = it
            }
        }
    }
}