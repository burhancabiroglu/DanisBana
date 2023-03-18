package com.danisbana.danisbanaapp.core.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessageServiceImpl: FirebaseMessagingService() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.e("TAG", "onMessageReceived: ${message.notification?.body} ", )
    }


    override fun onMessageSent(msgId: String) {
        super.onMessageSent(msgId)
    }
}