package com.danisbana.danisbanaapp.core.repo

import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.profile.AppUser
import com.danisbana.danisbanaapp.core.model.profile.UserInfo
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.service.FirebaseAuthService
import com.danisbana.danisbanaapp.domain.service.FirebaseDatabaseService
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseAuthRepoImpl @Inject constructor(
    private var authService: FirebaseAuthService,
    private var databaseService: FirebaseDatabaseService
) : FirebaseAuthRepo {
    override suspend fun loginAsync(loginRequest: LoginRequest): Deferred<Result<AppUser>> {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    val authResult = authService.login(loginRequest)
                    val userInfo = databaseService.getUserCredentials(authResult.getOrNull()?.user?.uid.toString())
                    val appUser = AppUser(
                        firebaseUser = authResult.getOrNull()?.user,
                        info = userInfo.getOrNull()
                    )
                    return@async Result.success(appUser)
                } catch (e: java.lang.Exception) {
                    return@async Result.failure(e)
                }
            }
        }
    }

    override suspend fun registerAsync(request: RegisterRequest): Deferred<Result<AuthResult>> {
        return withContext(Dispatchers.IO) {
            var result: Result<AuthResult>
            return@withContext async {
                try {
                    result = authService.register(request)
                    databaseService.createUserCredentials(
                        UserInfo(
                            id = result.getOrNull()?.user?.uid.toString(),
                            point = 100
                        )
                    )
                    return@async result
                } catch (e: java.lang.Exception) {
                    result = Result.failure(e)
                    return@async result
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
                    )
                }catch (e:Exception) {
                    return@async Result.failure(e)
                }
            }

        }

    }
}