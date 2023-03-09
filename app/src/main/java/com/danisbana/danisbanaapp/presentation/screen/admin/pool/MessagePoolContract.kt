package com.danisbana.danisbanaapp.presentation.screen.admin.pool

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.domain.base.BaseDialogState
import com.danisbana.danisbanaapp.domain.base.LoadingState

class MessagePoolState(
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
) {
    var pageLoading = LoadingState(false)
    var dialogState = BaseDialogState()
    var messages by mutableStateOf<List<MessageEntity>>(listOf())
}


data class MessagePoolActions(
    var routeConversation: (MessageEntity) -> Unit = {},
)


