package com.danisbana.danisbanaapp.domain.repo

import android.net.Uri
import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.profile.AppUser
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Deferred


interface FirebaseAuthRepo {
    suspend fun loginAsync(loginRequest: LoginRequest): Deferred<Result<AppUser>>
    suspend fun registerAsync(request: RegisterRequest): Deferred<Result<AuthResult>>
    fun signOut()
    fun getCurrentUser(): FirebaseUser?
    suspend fun getAppUserAsync(): Deferred<Result<AppUser>>
    suspend fun updateProfilePictureAsync(bytes: ByteArray): Deferred<Result<Void>>
}

