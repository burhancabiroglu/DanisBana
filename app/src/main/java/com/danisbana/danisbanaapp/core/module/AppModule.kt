package com.danisbana.danisbanaapp.core.module

import com.danisbana.danisbanaapp.core.repo.FirebaseAuthRepoImpl
import com.danisbana.danisbanaapp.core.repo.FirebaseConfigRepoImpl
import com.danisbana.danisbanaapp.core.service.FirebaseAuthServiceImpl
import com.danisbana.danisbanaapp.core.service.FirebaseConfigServiceImpl
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.repo.FirebaseConfigRepo
import com.danisbana.danisbanaapp.domain.service.FirebaseAuthService
import com.danisbana.danisbanaapp.domain.service.FirebaseConfigService
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

    @Provides
    @Singleton
    fun provideFirebaseConfigService(): FirebaseConfigService {
        return FirebaseConfigServiceImpl()
    }

    @Provides
    @Singleton
    fun provideFirebaseConfigRepo(firebaseConfigService: FirebaseConfigService): FirebaseConfigRepo {
        return FirebaseConfigRepoImpl(firebaseConfigService)
    }
}