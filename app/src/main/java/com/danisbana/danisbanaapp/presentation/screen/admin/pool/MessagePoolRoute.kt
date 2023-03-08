package com.danisbana.danisbanaapp.presentation.screen.admin.pool

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MessagePoolRoute(
    viewModel: MessagePoolViewModel = hiltViewModel()
) {
    val uiState by viewModel.stateFlow.collectAsState()
    val actions = rememberMessagePoolActions(viewModel)
    MessagePoolScreen(uiState, actions)
}


@Composable
fun rememberMessagePoolActions(viewModel: MessagePoolViewModel): MessagePoolActions {
    return remember(viewModel) {
        MessagePoolActions()
    }
}