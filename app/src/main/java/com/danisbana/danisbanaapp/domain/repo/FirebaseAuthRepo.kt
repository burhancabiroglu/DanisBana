package com.danisbana.danisbanaapp.domain.repo

import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Deferred


interface FirebaseAuthRepo {
    suspend fun loginAsync(loginRequest: LoginRequest): Deferred<Result<AuthResult>>
    suspend fun registerAsync(request: RegisterRequest): Deferred<Result<AuthResult>>
}