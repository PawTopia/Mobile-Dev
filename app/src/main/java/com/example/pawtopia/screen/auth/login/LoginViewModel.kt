package com.example.pawtopia.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pawtopia.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun login(
        email: String,
        password: String,
        success: () -> Unit = {},
        failed: () -> Unit = {}
    ) {
        viewModelScope.launch {
            authRepository.login(
                email = email,
                password = password,
                success = success,
                failed = failed
            )
        }
    }
}