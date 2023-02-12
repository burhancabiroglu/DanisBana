package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents ConversationScreen
 **/
class ConversationState

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

