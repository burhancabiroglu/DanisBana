package com.danisbana.danisbanaapp.presentation.screen.admin.pool

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.danisbana.danisbanaapp.presentation.screen.admin.conversation.AdminConversationActivity
import com.danisbana.danisbanaapp.presentation.screen.admin.conversation.AdminConversationArgs

@Composable
fun MessagePoolRoute(
    viewModel: MessagePoolViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.stateFlow.collectAsState()
    val actions = rememberMessagePoolActions(viewModel)

    actions.routeConversation = {
        AdminConversationActivity.launch(
            context,
            AdminConversationArgs(it)
        )
    }
    MessagePoolScreen(uiState, actions)
}


@Composable
fun rememberMessagePoolActions(viewModel: MessagePoolViewModel): MessagePoolActions {
    return remember(viewModel) {
        MessagePoolActions()
    }
}