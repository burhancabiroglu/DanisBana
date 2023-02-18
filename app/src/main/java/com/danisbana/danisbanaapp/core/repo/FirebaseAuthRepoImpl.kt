package com.danisbana.danisbanaapp.core.repo

import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.service.FirebaseAuthService
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseAuthRepoImpl @Inject constructor(
    private var firebaseAuthService: FirebaseAuthService
): FirebaseAuthRepo {

    override suspend fun loginAsync(loginRequest: LoginRequest): Deferred<Result<AuthResult>> {
        return withContext(Dispatchers.IO) {
            var result: Result<AuthResult>
            return@withContext async {
               try {
                   result = firebaseAuthService.login(loginRequest)
                   return@async result
               }catch (e: java.lang.Exception) {
                   result = Result.failure(e)
                   return@async result
               }
            }
        }
    }

    override suspend fun registerAsync(request: RegisterRequest): Deferred<Result<AuthResult>> {
        return withContext(Dispatchers.IO) {
            var result: Result<AuthResult>
            return@withContext async {
                try {
                    result = firebaseAuthService.register(request)
                    return@async result
                }catch (e: java.lang.Exception) {
                    result = Result.failure(e)
                    return@async result
                }
            }
        }
    }

    override fun signOut() {
        return firebaseAuthService.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuthService.getCurrentUser()
    }
}