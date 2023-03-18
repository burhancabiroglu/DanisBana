package com.danisbana.danisbanaapp.domain.service

import android.net.Uri
import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

interface FirebaseAuthService {
    val auth: FirebaseAuth get() = Firebase.auth
    val messaging: FirebaseMessaging get() = FirebaseMessaging.getInstance()

    suspend fun register(request: RegisterRequest): Result<AuthResult>
    suspend fun login(loginRequest: LoginRequest): Result<AuthResult>
    fun signOut()
    fun getCurrentUser(): FirebaseUser?
    suspend fun updateProfilePicture(uri: Uri): Result<Void>
    suspend fun initFCMToken(): Result<String>
}