package com.danisbana.danisbanaapp.domain.service

import com.danisbana.danisbanaapp.core.model.agreement.Agreement
import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig

interface FirebaseConfigService {
    val config: FirebaseRemoteConfig get() = Firebase.remoteConfig
    suspend fun getAgreementConfig(): Result<Agreement>
}