package com.danisbana.danisbanaapp.core.module

import com.danisbana.danisbanaapp.core.api.NotificationApi
import com.danisbana.danisbanaapp.core.repo.AdminRepoImpl
import com.danisbana.danisbanaapp.core.repo.FirebaseAuthRepoImpl
import com.danisbana.danisbanaapp.core.repo.FirebaseConfigRepoImpl
import com.danisbana.danisbanaapp.core.repo.FirebaseDatabaseRepoImpl
import com.danisbana.danisbanaapp.core.service.*
import com.danisbana.danisbanaapp.domain.repo.AdminRepo
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.repo.FirebaseConfigRepo
import com.danisbana.danisbanaapp.domain.repo.FirebaseDatabaseRepo
import com.danisbana.danisbanaapp.domain.service.FirebaseAuthService
import com.danisbana.danisbanaapp.domain.service.FirebaseConfigService
import com.danisbana.danisbanaapp.domain.service.FirebaseDatabaseService
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
    fun provideNotificationService(notificationApi: NotificationApi):PushNotificationService {
        return PushNotificationService(notificationApi)
    }


    @Provides
    @Singleton
    fun provideFirebaseAuthService(): FirebaseAuthService {
        return FirebaseAuthServiceImpl()
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

    @Provides
    @Singleton
    fun provideFirebaseDatabaseService(): FirebaseDatabaseService {
        return FirebaseDatabaseServiceImpl()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthRepo(
        firebaseAuthService: FirebaseAuthService,
        firebaseDatabaseService: FirebaseDatabaseService,
        notificationService: PushNotificationService,
    ): FirebaseAuthRepo {
        return FirebaseAuthRepoImpl(firebaseAuthService,firebaseDatabaseService,notificationService)
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabaseRepo(firebaseDatabaseService: FirebaseDatabaseService): FirebaseDatabaseRepo {
        return FirebaseDatabaseRepoImpl(firebaseDatabaseService)
    }

    @Provides
    @Singleton
    fun provideAdminRepo(
        firebaseAuthService: FirebaseAuthService,
        firebaseDatabaseService: FirebaseDatabaseService,
        notificationService: PushNotificationService
    ): AdminRepo {
        return AdminRepoImpl(firebaseAuthService,firebaseDatabaseService,notificationService)
    }
}