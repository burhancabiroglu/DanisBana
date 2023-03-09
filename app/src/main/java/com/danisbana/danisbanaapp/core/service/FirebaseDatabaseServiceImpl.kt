package com.danisbana.danisbanaapp.core.service

import android.net.Uri
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.core.model.message.MessageStatus
import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.danisbana.danisbanaapp.domain.service.FirebaseDatabaseService
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.DocumentReference
import com.google.gson.Gson
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class FirebaseDatabaseServiceImpl : FirebaseDatabaseService {
    override suspend fun createUserCredentials(info: UserInfo): Result<DocumentReference> {
        return suspendCancellableCoroutine { continuation ->
            val ref = firestore.collection("users").document(info.id)
            ref.set(info).addOnSuccessListener {
                    continuation.resume(Result.success(ref))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun getUserCredentials(uid: String): Result<UserInfo> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("users").document(uid).get().addOnSuccessListener {
                    val gson = Gson()
                    val obj = gson.fromJson(it.data.toString(), UserInfo::class.java)
                    continuation.resume(Result.success(obj))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun uploadPhoto(uid: String, bytes: ByteArray): Result<Uri> {
        val storageRef = storage.reference
        val mountainsRef = storageRef.child("profilePictures/$uid.jpg")
        return suspendCancellableCoroutine { continuation ->
            mountainsRef.putBytes(bytes).addOnSuccessListener { task ->
                    task.storage.downloadUrl.addOnSuccessListener {
                            continuation.resume(Result.success(it))
                        }.addOnFailureListener {
                            continuation.resumeWithException(it)
                        }
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun createMessage(message: MessageEntity): Result<DocumentReference> {
        return suspendCancellableCoroutine { continuation ->
            val ref = firestore.collection("messages").document(message.id)
            ref.set(message).addOnSuccessListener {
                    continuation.resume(Result.success(ref))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun getMessages(userId: String): Result<List<MessageEntity>> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("messages").whereEqualTo("senderId", userId).get()
                .addOnSuccessListener {
                    continuation.resume(Result.success(it.toObjects(MessageEntity::class.java)))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun getMessagesPool(): Result<List<MessageEntity>> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("messages").whereEqualTo("pool", true).get()
                .addOnSuccessListener {
                    continuation.resume(Result.success(it.toObjects(MessageEntity::class.java)))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun getEditorMessages(consultantId: String): Result<List<MessageEntity>> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("messages").whereEqualTo("consultantId", consultantId).get()
                .addOnSuccessListener {
                    continuation.resume(Result.success(it.toObjects(MessageEntity::class.java)))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun deleteMessage(id: String): Result<Void> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("messages").document(id).delete().addOnSuccessListener {
                    continuation.resume(Result.success(it))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun appendPoint(userId: String, currentValue: Int): Result<Void> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("users").document(userId).update("point", currentValue + 20)
                .addOnSuccessListener {
                    continuation.resume(Result.success(it))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun removePoint(userId: String, currentValue: Int): Result<Void> {
        return suspendCancellableCoroutine { continuation ->
            if (currentValue - 60 < 0) return@suspendCancellableCoroutine
            firestore.collection("users").document(userId).update("point", currentValue - 60)
                .addOnSuccessListener {
                    continuation.resume(Result.success(it))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun totalMessageCount(userId: String): Result<Int> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("messages").whereEqualTo("senderId", userId).count().get(
                AggregateSource.SERVER
            ).addOnSuccessListener {
                    continuation.resume(Result.success(it.count.toInt()))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }


    override suspend fun acceptedMessageCount(userId: String): Result<Int> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("messages")
                .whereEqualTo("senderId", userId)
                .whereIn("statusOrdinal", listOf(MessageStatus.ACCEPTED.ordinal, MessageStatus.ANSWERED.ordinal))
                .count()
                .get(AggregateSource.SERVER).addOnSuccessListener {
                    continuation.resume(Result.success(it.count.toInt()))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }
}