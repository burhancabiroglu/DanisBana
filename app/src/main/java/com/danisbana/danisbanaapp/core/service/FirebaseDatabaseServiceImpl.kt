package com.danisbana.danisbanaapp.core.service

import android.net.Uri
import android.util.Log
import androidx.work.Operation.State.IN_PROGRESS
import com.danisbana.danisbanaapp.application.Constants.MESSAGE_COST
import com.danisbana.danisbanaapp.core.model.message.Answer
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.core.model.message.MessageStatus
import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.danisbana.danisbanaapp.core.model.profile.UserRole
import com.danisbana.danisbanaapp.core.util.InsufficientUserPointException
import com.danisbana.danisbanaapp.core.util.UserNotRegisteredException
import com.danisbana.danisbanaapp.domain.service.FirebaseDatabaseService
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.auth.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.reflect.Type
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
                val obj = it.toObject(UserInfo::class.java)?: return@addOnSuccessListener continuation.resumeWithException(UserNotRegisteredException())
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

    override suspend fun appendPoint(
        userId: String,
        currentValue: Int,
        reward: Int
    ): Result<Void> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("users").document(userId).update("point", currentValue + reward)
                .addOnSuccessListener {
                    continuation.resume(Result.success(it))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun removePoint(userId: String, currentValue: Int): Result<Void> {
        return suspendCancellableCoroutine { continuation ->
            if (currentValue - MESSAGE_COST < 0) {
                val exception = InsufficientUserPointException()
                continuation.resumeWithException(exception)
                throw exception
            }
            else {
                firestore.collection("users").document(userId).update("point", currentValue - MESSAGE_COST)
                    .addOnSuccessListener {
                        continuation.resume(Result.success(it))
                    }.addOnFailureListener {
                        continuation.resumeWithException(it)
                    }
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

    override suspend fun updateMessageStatus(
        id: String,
        uid: String,
        status: MessageStatus
    ): Result<Void> {
        return suspendCancellableCoroutine { continuation ->
            val updates = hashMapOf<String,Any>(
                "statusOrdinal" to status.ordinal,
                "status" to status.name,
                "consultantId" to uid,
                "pool" to false
            )
            firestore.collection("messages").document(id)
                .update(updates)
                .addOnSuccessListener {
                    continuation.resume(Result.success(it))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun reloadMessage(id: String): Result<MessageEntity> {
        return suspendCancellableCoroutine { continuation ->
            firestore.collection("messages").document(id)
                .get()
                .addOnSuccessListener {
                    val message = it.toObject(MessageEntity::class.java)?:throw Exception("")
                    continuation.resume(Result.success(message))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun answerMessage(
        messageId: String,
        uid: String,
        answer: Answer
    ): Result<Void> {
        return suspendCancellableCoroutine { continuation ->
            val updates = hashMapOf(
                "consultantId" to uid,
                "answer" to answer
            )
            firestore.collection("messages").document(messageId)
                .update(updates)
                .addOnSuccessListener {
                    continuation.resume(Result.success(it))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun updateFCMToken(uid: String, token: String): Result<Void> {
        return suspendCancellableCoroutine { continuation ->
            val updates: Map<String,Any> = hashMapOf(
                "cloudToken" to token.trim()
            )
            firestore.collection("users").document(uid)
                .update(updates)
                .addOnSuccessListener {
                    continuation.resume(Result.success(it))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }
}