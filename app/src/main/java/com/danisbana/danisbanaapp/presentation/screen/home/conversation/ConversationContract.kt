package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.runtime.*
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.domain.base.LoadingState


class ConversationState {
    var channelName: String = ""
    var message by mutableStateOf<MessageEntity?>(null)
    var loadingState = LoadingState(false)
}

data class ConversationActions(
    val onClick: () -> Unit = {},
    var onBackClick: () -> Unit = {}
)

@Composable
fun rememberConversationActions(viewModel: ConversationViewModel): ConversationActions {
    return remember(viewModel) {
        ConversationActions()
    }
}