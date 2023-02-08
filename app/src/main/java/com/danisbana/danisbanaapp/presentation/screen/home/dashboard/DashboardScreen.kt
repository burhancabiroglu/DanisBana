package com.danisbana.danisbanaapp.presentation.screen.home.dashboard

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DashboardScreen(
    state: DashboardState = DashboardState(),
    viewModel: DashboardViewModel = hiltViewModel()
) {
   Scaffold {
       it.calculateBottomPadding()
   }
}

@Composable
@Preview(name = "Dashboard")
private fun DashboardScreenPreview() {
    DashboardScreen()
}

