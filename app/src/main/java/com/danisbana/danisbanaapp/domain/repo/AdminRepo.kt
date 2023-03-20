package com.danisbana.danisbanaapp.domain.repo

import com.danisbana.danisbanaapp.core.model.message.Answer
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.core.model.message.MessageStatus
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Deferred

interface AdminRepo {
    suspend fun getMessagesPoolAsync(): Deferred<Result<List<MessageEntity>>>
    suspend fun getPersonalMessagesAsync(): Deferred<Result<List<MessageEntity>>>
    suspend fun deleteMessageAsync(id: String): Deferred<Result<Void>>
    suspend fun reloadMessageAsync(id: String): Deferred<Result<MessageEntity>>
    suspend fun updateMessageStatusAsync(
        id: String,
        senderToken: String,
        status: MessageStatus): Deferred<Result<Void>>
    suspend fun answerMessageAsync(messageId: String, answer: Answer): Deferred<Result<Void>>
    fun getCurrentUser(): FirebaseUser?
}