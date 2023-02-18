package com.danisbana.danisbanaapp.domain.service

import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface FirebaseDatabaseService {
    val firestore: FirebaseFirestore get() = Firebase.firestore
    suspend fun createUserCredentials(info: UserInfo): Result<DocumentReference>
    suspend fun getUserCredentials(uid: String): Result<UserInfo>
}