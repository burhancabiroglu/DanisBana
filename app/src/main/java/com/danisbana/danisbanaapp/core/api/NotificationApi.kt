package com.danisbana.danisbanaapp.core.api

import com.danisbana.danisbanaapp.core.model.message.PostMessage
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NotificationApi {

    @POST("fcm/send")
    suspend fun postNotification (
        @Body notification: PostMessage
    ): Response<ResponseBody>
}