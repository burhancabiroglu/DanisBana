package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ConversationCoordinator(
    val viewModel: ConversationViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {

    }
}

@Composable
fun rememberConversationCoordinator(
    viewModel: ConversationViewModel = hiltViewModel()
): ConversationCoordinator {
    return remember(viewModel) {
        ConversationCoordinator(
            viewModel = viewModel
        )
    }
}