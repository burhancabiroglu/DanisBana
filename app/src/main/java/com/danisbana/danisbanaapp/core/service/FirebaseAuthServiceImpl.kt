package com.danisbana.danisbanaapp.core.service

import android.util.Log
import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.danisbana.danisbanaapp.domain.service.FirebaseAuthService
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class FirebaseAuthServiceImpl: FirebaseAuthService {

    override suspend fun register(request: RegisterRequest): Result<AuthResult> {
        return suspendCancellableCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(request.email,request.password)
                .addOnSuccessListener {
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(request.fullName)
                        .build()
                    it.user?.updateProfile(profileUpdates)
                    //it.user?.sendEmailVerification()
                    continuation.resume(Result.success(it))
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun login(loginRequest: LoginRequest): Result<AuthResult> {
        return suspendCancellableCoroutine { continuation ->
            auth.signInWithEmailAndPassword(loginRequest.email,loginRequest.password)
                .addOnSuccessListener {
                    continuation.resume(Result.success(it))
                }
                .addOnFailureListener {
                    Log.e("TAG", "login: $it", )
                    continuation.resumeWithException(it)
                }
        }
    }

    override fun signOut() {
        return auth.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}