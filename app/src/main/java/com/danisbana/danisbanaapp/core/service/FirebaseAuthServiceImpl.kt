package com.danisbana.danisbanaapp.core.service

import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.danisbana.danisbanaapp.domain.service.FirebaseAuthService
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class FirebaseAuthServiceImpl: FirebaseAuthService {

    override suspend fun register(request: RegisterRequest): Result<AuthResult> {
        return suspendCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(request.email,request.password)
                .addOnSuccessListener {
                    continuation.resume(Result.success(it))
                }
                .addOnFailureListener {
                    continuation.resume(Result.failure(it))
                }
        }
    }

    override suspend fun login(loginRequest: LoginRequest): Result<AuthResult> {
        return suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(loginRequest.email,loginRequest.password)
                .addOnSuccessListener {
                    continuation.resume(Result.success(it))
                }
                .addOnFailureListener {
                    continuation.resume(Result.failure(it))
                }
        }
    }
}