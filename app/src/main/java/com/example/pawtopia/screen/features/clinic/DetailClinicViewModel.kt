package com.example.pawtopia.screen.features.clinic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pawtopia.common.util.DataDummy
import com.example.pawtopia.data.model.Clinic
import com.example.pawtopia.domain.repository.PawtopiaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailClinicViewModel @Inject constructor(
    private val pawtopiaRepository: PawtopiaRepository
) : ViewModel() {
    private val _result: MutableStateFlow<Clinic> = MutableStateFlow(DataDummy.dummyClinic.first())
    val result: StateFlow<Clinic> = _result

    fun getClinicById(clinidId: Int) {
        viewModelScope.launch {
            pawtopiaRepository.clinicById(clinidId).collect {
                _result.value = it
            }
        }
    }
}