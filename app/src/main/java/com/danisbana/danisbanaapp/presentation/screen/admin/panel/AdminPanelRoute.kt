package com.danisbana.danisbanaapp.presentation.screen.admin.panel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun AdminPanelRoute(
    parentNavController: NavHostController,
    viewModel: AdminPanelViewModel = hiltViewModel()
) {
    val uiState by viewModel.stateFlow.collectAsState()
    val actions = rememberAdminPanelActions(viewModel)
    AdminPanelScreen(uiState, actions,parentNavController)
}


@Composable
fun rememberAdminPanelActions(viewModel: AdminPanelViewModel): AdminPanelActions {
    return remember(viewModel) {
        AdminPanelActions()
    }
}