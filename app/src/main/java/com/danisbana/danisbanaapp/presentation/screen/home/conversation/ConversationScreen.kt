package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ConversationScreen(
    state: ConversationState = ConversationState(),
    actions: ConversationActions = ConversationActions()
) {
    // TODO UI Logic
}

@Composable
@Preview(name = "Conversation")
private fun ConversationScreenPreview() {
    ConversationScreen()
}

