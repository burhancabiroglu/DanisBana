package com.danisbana.danisbanaapp.domain.repo

import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.core.model.profile.AppUser
import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.Deferred


interface FirebaseAuthRepo {
    val userCacheValue: AppUser?
    suspend fun loginAsync(loginRequest: LoginRequest): Deferred<Result<AppUser>>
    suspend fun registerAsync(request: RegisterRequest): Deferred<Result<AuthResult>>
    fun signOut()
    fun getCurrentUser(): FirebaseUser?
    suspend fun getAppUserAsync(): Deferred<Result<AppUser>>
    suspend fun updateProfilePictureAsync(bytes: ByteArray): Deferred<Result<Void>>
    suspend fun createMessageAsync(title: String,content: String): Deferred<Result<DocumentReference>>
    suspend fun getUserMessagesAsync(): Deferred<Result<List<MessageEntity>>>
    suspend fun deleteMessageAsync(id: String): Deferred<Result<Void>>
    suspend fun appendPointAsync(reward: Int): Deferred<Result<Void>>
    suspend fun removePointAsync(): Deferred<Result<Void>>
    suspend fun getTotalMessageCountAsync(): Deferred<Result<Int>>
    suspend fun getAcceptedMessageCountAsync(): Deferred<Result<Int>>
    suspend fun updateFCMTokenAsync(token: String): Deferred<Result<String>>
}

