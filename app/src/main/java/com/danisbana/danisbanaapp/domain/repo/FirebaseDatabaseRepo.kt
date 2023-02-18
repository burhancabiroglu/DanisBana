package com.danisbana.danisbanaapp.domain.repo

import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.Deferred


interface FirebaseDatabaseRepo {
    suspend fun createUserCredentialsAsync(userInfo: UserInfo): Deferred<Result<DocumentReference>>
}