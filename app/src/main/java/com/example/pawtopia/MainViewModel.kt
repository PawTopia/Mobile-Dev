package com.example.pawtopia

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pawtopia.domain.repository.AuthRepository
import com.example.pawtopia.domain.repository.DataStoreRepository
import com.example.pawtopia.navigation.graphs.Graph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Graph.NONE)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnboardingState().collect { isCompleted ->
                if (isCompleted) {
                    _startDestination.value =
                        if (authRepository.hasUser) Graph.MAIN else Graph.AUTHENTICATION
                } else {
                    _startDestination.value = Graph.ONBOARDING
                }
            }
            _isLoading.value = false
        }
    }

}