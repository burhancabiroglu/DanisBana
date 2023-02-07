package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DashboardRoute(
    coordinator: DashboardViewModel = hiltViewModel()
) {
    val uiState by coordinator.stateFlow.collectAsState(DashboardState())
    val actions = rememberDashboardActions(coordinator)
    DashboardScreen(uiState, actions)
}


@Composable
fun rememberDashboardActions(viewModel: DashboardViewModel): DashboardActions {
    return remember(viewModel) {
        DashboardActions()
    }
}