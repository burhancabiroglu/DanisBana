package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.toMutableStateList
import com.danisbana.danisbanaapp.core.model.message.MessageModel


/**
 * UI State that represents ConversationScreen
 **/
class ConversationState(
    val channelName: String = "",
    val channelMembers: Int = 0,
    initialMessages: List<MessageModel> = listOf()
) {
    private val _messages: MutableList<MessageModel> = initialMessages.toMutableStateList()
    val messages: List<MessageModel> = _messages

    fun addMessage(msg: MessageModel) {
        _messages.add(0, msg)
    }
}

/**
 * Conversation Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ConversationActions(
    val onClick: () -> Unit = {}
)

/**
 * Compose Utility to retrieve actions from nested components
 **/
val LocalConversationActions = staticCompositionLocalOf<ConversationActions> {
    error("{NAME} Actions Were not provided, make sure ProvideConversationActions is called")
}

@Composable
fun ProvideConversationActions(actions: ConversationActions, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalConversationActions provides actions) {
        content.invoke()
    }
}