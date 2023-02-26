package com.danisbana.danisbanaapp.presentation.screen.home.messages

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.domain.base.BaseDialogState
import com.danisbana.danisbanaapp.domain.base.LoadingState

class MessagesState(
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
) {
    var pageLoading = LoadingState(false)
    var dialogState = BaseDialogState()
    var messages by mutableStateOf<List<MessageEntity>>(listOf())
}


data class MessagesActions(
    var routeConsultant: () -> Unit = {},
    var routeConversation: (MessageEntity) -> Unit = {},
    var deleteMessage:(id:String) -> Unit = {}
)
