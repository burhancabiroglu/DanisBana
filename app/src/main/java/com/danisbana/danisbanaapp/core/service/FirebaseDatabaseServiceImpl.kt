package com.danisbana.danisbanaapp.core.service

import android.net.Uri
import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.danisbana.danisbanaapp.domain.service.FirebaseDatabaseService
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.storage.UploadTask.TaskSnapshot
import com.google.gson.Gson
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class FirebaseDatabaseServiceImpl : FirebaseDatabaseService {
    override suspend fun createUserCredentials(info: UserInfo): Result<DocumentReference> {
        return suspendCancellableCoroutine { continuation ->
            val ref = firestore.collection("users").document(info.id)
            ref.set(info)
                .addOnSuccessListener {
                    continuation.resume(Result.success(ref))
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun getUserCredentials(uid: String): Result<UserInfo> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("users").document(uid).get()
                .addOnSuccessListener {
                    val gson = Gson()
                    val obj = gson.fromJson<UserInfo>(it.data.toString(),UserInfo::class.java)
                    continuation.resume(Result.success(obj))
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun uploadPhoto(uid: String, bytes: ByteArray): Result<Uri> {
        val storageRef = storage.reference
        val mountainsRef = storageRef.child("profilePictures/$uid.jpg")
        return suspendCancellableCoroutine { continuation ->
            mountainsRef.putBytes(bytes)
                .addOnSuccessListener {
                    it.storage.downloadUrl
                        .addOnSuccessListener {
                            continuation.resume(Result.success(it))
                        }
                        .addOnFailureListener {
                            continuation.resumeWithException(it)
                        }
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }
}