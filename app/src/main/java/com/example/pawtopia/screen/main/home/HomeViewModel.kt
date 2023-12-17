package com.example.pawtopia.screen.main.home

import androidx.lifecycle.ViewModel
import com.example.pawtopia.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    authRepository: AuthRepository
) : ViewModel() {

    val userDisplayName = authRepository.userData?.displayName
}