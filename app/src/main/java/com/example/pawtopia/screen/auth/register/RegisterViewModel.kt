package com.example.pawtopia.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pawtopia.common.state.UiState
import com.example.pawtopia.domain.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    fun register(
        name: String,
        email: String,
        password: String,
        success: () -> Unit = {},
        failed: (Task<AuthResult>) -> Unit = {}
    ) {
        viewModelScope.launch {
            authRepository.register(
                name = name,
                email = email,
                password = password,
                success = success,
                failed = failed
            )
        }
    }
}