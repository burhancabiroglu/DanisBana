package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ConversationRoute(
    viewModel: ConversationViewModel = hiltViewModel(),
    navController: NavHostController

) {
    val uiState by viewModel.stateFlow.collectAsState()
    val actions = rememberConversationActions(viewModel)
    ConversationScreen(
        state = uiState,
        actions =  actions,
        navController = navController
    )
}


@Composable
fun rememberConversationActions(viewModel: ConversationViewModel): ConversationActions {
    return remember(viewModel) {
        ConversationActions()
    }
}