package com.danisbana.danisbanaapp.core.repo

import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.domain.repo.AdminRepo
import com.danisbana.danisbanaapp.domain.service.FirebaseAuthService
import com.danisbana.danisbanaapp.domain.service.FirebaseDatabaseService
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdminRepoImpl @Inject constructor(
    private var authService: FirebaseAuthService,
    private var databaseService: FirebaseDatabaseService
) : AdminRepo {
    override suspend fun getMessagesPoolAsync(): Deferred<Result<List<MessageEntity>>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    return@async databaseService.getMessagesPool()
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun getPersonalMessagesAsync(): Deferred<Result<List<MessageEntity>>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val user = getCurrentUser() ?: return@async Result.failure(Exception(""))
                    return@async databaseService.getEditorMessages(user.uid)
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun answerMessage() {
    }

    override suspend fun blockUser() {
    }

    override suspend fun updateMessageStatus() {

    }

    override suspend fun deleteMessageAsync(id: String): Deferred<Result<Void>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    return@async databaseService.deleteMessage(id)
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return authService.getCurrentUser()
    }
}