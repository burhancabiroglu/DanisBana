package com.danisbana.danisbanaapp.presentation.screen.admin.conversation

import androidx.compose.runtime.*
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.domain.base.LoadingState

class AdminConversationState {
    var channelName: String = ""
    var message by mutableStateOf<MessageEntity?>(null)
    var loadingState = LoadingState(false)
}

data class AdminConversationActions(
    var onBackClick: () -> Unit = {},
    var confirmAction: () -> Unit = {},
    var rejectAction: () -> Unit = {},
    var onMessageSent: (String) -> Unit = {}
)

@Composable
fun rememberAdminConversationActions(viewModel: AdminConversationViewModel): AdminConversationActions {
    return remember(viewModel) {
        AdminConversationActions(
            onMessageSent = viewModel::answerMessage
        )
    }
}