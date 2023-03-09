package com.danisbana.danisbanaapp.presentation.screen.admin.conversation

import android.os.Parcelable
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdminConversationArgs(
    val message: MessageEntity
): Parcelable