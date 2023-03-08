package com.danisbana.danisbanaapp.domain.repo

import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Deferred

interface AdminRepo {
    suspend fun getMessagesPoolAsync(): Deferred<Result<List<MessageEntity>>>
    suspend fun getPersonalMessagesAsync(): Deferred<Result<List<MessageEntity>>>
    suspend fun answerMessage()
    suspend fun blockUser()
    suspend fun updateMessageStatus()
    suspend fun deleteMessageAsync(id: String): Deferred<Result<Void>>
    fun getCurrentUser(): FirebaseUser?
}