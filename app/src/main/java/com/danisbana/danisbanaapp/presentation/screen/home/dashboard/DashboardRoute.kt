package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DashboardRoute(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.stateFlow.collectAsState(DashboardState())
    DashboardScreen(uiState, viewModel)
    val uiController = rememberSystemUiController()
    DisposableEffect(Unit){
        uiController.isNavigationBarVisible = true
        uiController.isStatusBarVisible = true
        onDispose {  }
    }
}
