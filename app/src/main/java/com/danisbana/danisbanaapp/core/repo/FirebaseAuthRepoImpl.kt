package com.danisbana.danisbanaapp.core.repo

import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.core.model.profile.AppUser
import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.danisbana.danisbanaapp.core.util.FirebaseEmailVerificationException
import com.danisbana.danisbanaapp.core.util.UserNotRegisteredException
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.service.FirebaseAuthService
import com.danisbana.danisbanaapp.domain.service.FirebaseDatabaseService
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject

class FirebaseAuthRepoImpl @Inject constructor(
    private var authService: FirebaseAuthService,
    private var databaseService: FirebaseDatabaseService
) : FirebaseAuthRepo {

    private val userCache = MutableStateFlow<AppUser?>(null)
    override val userCacheValue: AppUser? get() = userCache.value

    override suspend fun loginAsync(loginRequest: LoginRequest): Deferred<Result<AppUser>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val authResult = authService.login(loginRequest)
                    if(authResult.getOrNull()?.user?.isEmailVerified == false) throw FirebaseEmailVerificationException(
                        "Kullanıcı E-Postası Doğrulanmamış"
                    )
                    val userInfo = databaseService.getUserCredentials(authResult.getOrNull()?.user?.uid.toString())
                    val appUser = AppUser(
                        firebaseUser = authResult.getOrNull()?.user,
                        info = userInfo.getOrNull()
                    )
                    userCache.value = appUser
                    return@async Result.success(appUser)
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun registerAsync(request: RegisterRequest): Deferred<Result<AuthResult>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val authResult = authService.register(request)
                    databaseService.createUserCredentials(
                        UserInfo(
                            id = authResult.getOrNull()?.user?.uid.toString(),
                            point = 100
                        )
                    )
                    authResult.getOrNull()?.user?.sendEmailVerification()
                    return@async authResult
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override fun signOut() {
        return authService.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return authService.getCurrentUser()
    }

    override suspend fun getAppUserAsync(): Deferred<Result<AppUser>> {
        val currentUser = authService.getCurrentUser()
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val credentials = databaseService.getUserCredentials(currentUser?.uid.toString()).getOrNull()
                    return@async Result.success(
                        AppUser(
                            currentUser,
                            credentials
                        )
                    ).also {
                        userCache.value = it.getOrNull()
                    }
                } catch (e:Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun updateProfilePictureAsync(bytes: ByteArray): Deferred<Result<Void>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val user = getCurrentUser()?:return@async Result.failure(UserNotRegisteredException())
                    val uri = databaseService.uploadPhoto(
                        user.uid,
                        bytes
                    ).getOrNull()!!
                    return@async authService.updateProfilePicture(uri)
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun createMessageAsync(title: String, content: String): Deferred<Result<DocumentReference>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val user = getCurrentUser() ?: return@async Result.failure(UserNotRegisteredException())
                    val date = Date()
                    val message = MessageEntity(
                        timestamp = date.time,
                        senderId = user.uid,
                        title = title,
                        content = content
                    )
                    this@FirebaseAuthRepoImpl.removePointAsync().await().exceptionOrNull()?.let { throw it }
                    return@async databaseService.createMessage(message)
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun getUserMessagesAsync(): Deferred<Result<List<MessageEntity>>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val user = getCurrentUser() ?: return@async Result.failure(UserNotRegisteredException())
                    return@async databaseService.getMessages(user.uid)
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
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

    override suspend fun appendPointAsync(): Deferred<Result<Void>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val user = getAppUserAsync().await().getOrNull() ?:return@async Result.failure(UserNotRegisteredException())
                    return@async databaseService.appendPoint(
                        user.info?.id.toString(),
                        user.info?.point?:0
                    )
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun removePointAsync(): Deferred<Result<Void>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val user = getAppUserAsync().await().getOrNull() ?:return@async Result.failure(UserNotRegisteredException())
                    return@async databaseService.removePoint(
                        user.firebaseUser?.uid.toString(),
                        user.info?.point?:0
                    )
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun getTotalMessageCountAsync(): Deferred<Result<Int>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val user = getCurrentUser() ?: return@async Result.failure(UserNotRegisteredException())
                    return@async databaseService.totalMessageCount(user.uid)
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun getAcceptedMessageCountAsync(): Deferred<Result<Int>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val user = getCurrentUser() ?: return@async Result.failure(UserNotRegisteredException())
                    return@async databaseService.acceptedMessageCount(user.uid)
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun initFCMTokenAsync(): Deferred<Result<String>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val user = getCurrentUser() ?: return@async Result.failure(UserNotRegisteredException())
                    return@async authService.initFCMToken()
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }
}