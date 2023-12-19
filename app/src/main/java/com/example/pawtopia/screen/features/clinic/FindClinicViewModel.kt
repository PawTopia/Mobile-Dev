package com.example.pawtopia.screen.features.clinic

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pawtopia.common.state.Resource
import com.example.pawtopia.data.model.Clinic
import com.example.pawtopia.domain.repository.PawtopiaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindClinicViewModel @Inject constructor(
    private val pawtopiaRepository: PawtopiaRepository
) : ViewModel() {

    private val _result: MutableStateFlow<Resource<List<Clinic>>> =
        MutableStateFlow(Resource.Loading)
    val result: StateFlow<Resource<List<Clinic>>> get() = _result

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllClinic() {
        viewModelScope.launch {
            pawtopiaRepository.getAllClinic()
                .catch {
                    _result.value = Resource.Error(it.message.toString())
                }
                .collect { clinic ->
                    _result.value = Resource.Success(clinic)
                }
        }
    }

    init {
        getAllClinic()
    }

    fun searchClinic(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            pawtopiaRepository.searchClinic(newQuery)
                .catch {
                    _result.value = Resource.Error(it.message.toString())
                }
                .collect { clinic ->
                    _result.value = Resource.Success(clinic)
                }
        }
    }
}