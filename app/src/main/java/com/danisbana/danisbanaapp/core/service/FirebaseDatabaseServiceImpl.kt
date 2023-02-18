package com.danisbana.danisbanaapp.core.service

import android.util.Log
import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.danisbana.danisbanaapp.domain.service.FirebaseDatabaseService
import com.google.firebase.firestore.DocumentReference
import com.google.gson.Gson
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class FirebaseDatabaseServiceImpl : FirebaseDatabaseService {
    override suspend fun createUserCredentials(info: UserInfo): Result<DocumentReference> {
        return suspendCoroutine { continuation ->
            val ref = firestore.collection("users").document(info.id)
            ref.set(info)
                .addOnSuccessListener {
                    continuation.resume(Result.success(ref))
                }
                .addOnFailureListener {
                    continuation.resume(Result.failure(it))
                }
        }
    }

    override suspend fun getUserCredentials(uid: String): Result<UserInfo> {
        return suspendCoroutine { continuation ->
            firestore.collection("users").document(uid).get()
                .addOnSuccessListener {
                    val gson = Gson()
                    val obj = gson.fromJson<UserInfo>(it.data.toString(),UserInfo::class.java)
                    Log.e("TAG", "getUserCredentials: $obj", )
                    continuation.resume(Result.success(obj))
                }
                .addOnFailureListener {
                    continuation.resume(Result.failure(it))
                }
        }
    }
}