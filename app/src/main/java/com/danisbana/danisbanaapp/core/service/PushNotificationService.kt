package com.danisbana.danisbanaapp.core.service

import com.danisbana.danisbanaapp.core.api.NotificationApi
import com.danisbana.danisbanaapp.core.model.message.PostMessage
import com.danisbana.danisbanaapp.core.util.NetworkHandler
import javax.inject.Inject

class PushNotificationService @Inject constructor(
    private val api: NotificationApi
) {
    suspend fun postAsync(notification: PostMessage) = NetworkHandler.performOperationAsync {
        api.postNotification(notification)
    }
}