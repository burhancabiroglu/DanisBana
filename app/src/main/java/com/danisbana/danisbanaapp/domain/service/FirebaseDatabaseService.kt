package com.danisbana.danisbanaapp.domain.service

import android.net.Uri
import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.google.common.primitives.Bytes
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage

interface FirebaseDatabaseService {
    val firestore: FirebaseFirestore get() = Firebase.firestore
    val storage: FirebaseStorage get() = Firebase.storage
    suspend fun createUserCredentials(info: UserInfo): Result<DocumentReference>
    suspend fun getUserCredentials(uid: String): Result<UserInfo>
    suspend fun uploadPhoto(uid: String,bytes: ByteArray): Result<Uri>
}