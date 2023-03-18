package com.danisbana.danisbanaapp.application

import android.app.Application
import android.util.Log
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DanisBanaApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.messaging.isAutoInitEnabled = true
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}