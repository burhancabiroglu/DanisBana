package com.danisbana.danisbanaapp.presentation.screen.home.messages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MessagesRoute(
    viewModel: MessagesViewModel = hiltViewModel()
) {
    val uiState by viewModel.stateFlow.collectAsState(MessagesState())
    MessagesScreen(uiState, viewModel)
}
