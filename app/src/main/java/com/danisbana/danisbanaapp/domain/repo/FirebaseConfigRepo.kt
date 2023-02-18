package com.danisbana.danisbanaapp.domain.repo

import com.danisbana.danisbanaapp.core.model.agreement.Agreement
import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Deferred


interface FirebaseConfigRepo {
    suspend fun getAgreementConfigAsync(): Deferred<Result<Agreement>>
}