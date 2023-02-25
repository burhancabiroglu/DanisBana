package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import android.os.Message
import android.os.Parcelable
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConversationArgs(
    val message: MessageEntity
): Parcelable