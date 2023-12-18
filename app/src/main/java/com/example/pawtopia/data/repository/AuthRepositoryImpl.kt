package com.example.pawtopia.data.repository

import com.example.pawtopia.domain.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthRepository {
    override val hasUser: Boolean
        get() = auth.currentUser != null
    override val userData: FirebaseUser?
        get() = auth.currentUser

    override fun user(): FirebaseUser? = auth.currentUser
    override suspend fun logout() {
        auth.signOut()
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        success: () -> Unit,
        failed: (Task<AuthResult>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    user?.updateProfile(userProfileChangeRequest {
                        displayName = name
                    })
                    success()
                } else {
                    failed(task)
                }
            }
    }

    override suspend fun login(
        email: String,
        password: String,
        success: () -> Unit,
        failed: () -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    success()
                } else {
                    failed()
                }
            }
    }

}