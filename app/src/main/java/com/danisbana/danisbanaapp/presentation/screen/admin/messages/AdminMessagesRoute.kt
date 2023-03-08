package com.danisbana.danisbanaapp.presentation.screen.admin.messages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AdminMessagesRoute(
    viewModel: AdminMessagesViewModel = hiltViewModel()
) {
    val uiState by viewModel.stateFlow.collectAsState(AdminMessagesState())
    val actions = rememberAdminMessagesActions(viewModel)
    AdminMessagesScreen(uiState, actions)
}


@Composable
fun rememberAdminMessagesActions(viewModel: AdminMessagesViewModel): AdminMessagesActions {
    return remember(viewModel) {
        AdminMessagesActions()
    }
}