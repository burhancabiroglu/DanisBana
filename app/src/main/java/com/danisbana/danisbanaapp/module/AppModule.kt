package com.danisbana.danisbanaapp.module

import com.danisbana.danisbanaapp.core.repo.FirebaseAuthRepoImpl
import com.danisbana.danisbanaapp.core.service.FirebaseAuthServiceImpl
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.service.FirebaseAuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuthService(): FirebaseAuthService {
        return FirebaseAuthServiceImpl()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthRepo(firebaseAuthService: FirebaseAuthService): FirebaseAuthRepo {
        return FirebaseAuthRepoImpl(firebaseAuthService)
    }
}