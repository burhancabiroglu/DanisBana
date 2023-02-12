package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun ConversationRoute(
    coordinator: ConversationCoordinator = rememberConversationCoordinator()
) {
    // State observing and declarations
    //val uiState by coordinator.screenStateFlow.collectAsState(ConversationState())

    // UI Actions
    val actions = rememberConversationActions(coordinator)

    // UI Rendering
    ConversationScreen(actions =  actions)
}


@Composable
fun rememberConversationActions(coordinator: ConversationCoordinator): ConversationActions {
    return remember(coordinator) {
        ConversationActions(
            onClick = coordinator::doStuff
        )
    }
}