package com.danisbana.danisbanaapp.domain.service

import android.net.Uri
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

interface FirebaseDatabaseService {
    val firestore: FirebaseFirestore get() = Firebase.firestore
    val storage: FirebaseStorage get() = Firebase.storage
    suspend fun createUserCredentials(info: UserInfo): Result<DocumentReference>
    suspend fun getUserCredentials(uid: String): Result<UserInfo>
    suspend fun uploadPhoto(uid: String,bytes: ByteArray): Result<Uri>
    suspend fun createMessage(message: MessageEntity): Result<DocumentReference>
    suspend fun getMessages(userId: String): Result<List<MessageEntity>>
    suspend fun deleteMessage(id: String): Result<Void>
    suspend fun appendMessageCount(userId: String, currentValue: Int): Result<Void>
    suspend fun popMessageCount(userId: String, currentValue: Int): Result<Void>
    suspend fun appendPoint(userId: String, currentValue: Int): Result<Void>
    suspend fun popPoint(userId: String, currentValue: Int): Result<Void>
}