package com.example.pawtopia.screen.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pawtopia.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val user : FirebaseUser? = authRepository.userData
    val displayName = user?.displayName
    val email = user?.email

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

}