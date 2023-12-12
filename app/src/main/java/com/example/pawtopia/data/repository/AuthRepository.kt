package com.example.pawtopia.data.repository

import com.example.pawtopia.common.state.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PawtopiaRepository {
//    fun register(name: String, email: String, password: String): Flow<UiState<String>> = flow {
//        emit(UiState.Loading)
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                val user = Firebase.auth.currentUser
//                user?.updateProfile(userProfileChangeRequest {
//                    displayName = name
//                })
//                emit(UiState.Success("Success"))
//            }
//    }
}