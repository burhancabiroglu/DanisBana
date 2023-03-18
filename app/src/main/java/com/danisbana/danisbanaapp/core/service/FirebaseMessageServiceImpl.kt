package com.danisbana.danisbanaapp.core.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessageServiceImpl: FirebaseMessagingService() {

    override fun onCreate() {
        super.onCreate()
        Log.e("TAG", "onCreate: ", )
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("TAG", "onNewToken: $token ", )
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }


    override fun onMessageSent(msgId: String) {
        super.onMessageSent(msgId)
    }
}