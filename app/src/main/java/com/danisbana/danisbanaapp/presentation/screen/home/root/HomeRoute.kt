package com.danisbana.danisbanaapp.presentation.screen.home.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.stateFlow.collectAsState(HomeState())
    val actions = rememberHomeActions(viewModel)
    val homeNavController = rememberNavController()
    HomeScreen(uiState, actions,homeNavController)
}


@Composable
fun rememberHomeActions(viewModel: HomeViewModel): HomeActions {
    return remember(viewModel) {
        HomeActions()
    }
}