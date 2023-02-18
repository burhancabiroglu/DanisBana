package com.danisbana.danisbanaapp.core.repo

import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.danisbana.danisbanaapp.domain.repo.FirebaseDatabaseRepo
import com.danisbana.danisbanaapp.domain.service.FirebaseDatabaseService
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseDatabaseRepoImpl @Inject constructor(
    private var databaseService: FirebaseDatabaseService
) : FirebaseDatabaseRepo {

    override suspend fun createUserCredentialsAsync(userInfo: UserInfo): Deferred<Result<DocumentReference>> {
        return withContext(Dispatchers.IO) {
            var result: Result<DocumentReference>
            return@withContext async {
                try {
                    result = databaseService.createUserCredentials(userInfo)
                    return@async result
                } catch (e: Exception) {
                    result = Result.failure(e)
                    return@async result
                }
            }
        }
    }
}