package com.danisbana.danisbanaapp.core.service

import com.danisbana.danisbanaapp.core.model.agreement.Agreement
import com.danisbana.danisbanaapp.domain.service.FirebaseConfigService
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class FirebaseConfigServiceImpl: FirebaseConfigService {
    override suspend fun getAgreementConfig(): Result<Agreement> {
        config.fetch().await()
        config.activate().await()
        return suspendCoroutine<Result<Agreement>> { continuation ->
            val key = "agreement_config"
            val gson = Gson()
            val value = config.getValue(key).asString()
            val json = gson.fromJson<Agreement>(value.toString(),Agreement::class.java)
            continuation.resume(Result.success(json))
        }
    }
}