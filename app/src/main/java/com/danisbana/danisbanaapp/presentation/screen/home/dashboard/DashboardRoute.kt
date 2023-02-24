package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeActions

@Composable
fun DashboardRoute(
    viewModel: DashboardViewModel = hiltViewModel(),
    homeActions: HomeActions = HomeActions()
) {
    val uiState by viewModel.stateFlow.collectAsState(DashboardState())
    val actions = rememberDashboardActions(viewModel)
    actions.routeConsultant = homeActions.routeConsultant
    DashboardScreen(uiState, actions)
}


@Composable
fun rememberDashboardActions(viewModel: DashboardViewModel): DashboardActions {
    return remember(viewModel) {
        DashboardActions()
    }
}