package com.example.pawtopia.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val hasUser: Boolean
    val userData: FirebaseUser?
    fun user(): FirebaseUser?


    suspend fun logout()

    suspend fun register(
        name: String,
        email: String,
        password: String,
        success: () -> Unit = {},
        failed: (Task<AuthResult>) -> Unit = {}
    )

    suspend fun login(
        email: String,
        password: String,
        success: () -> Unit = {},
        failed: () -> Unit = {}
    )
}