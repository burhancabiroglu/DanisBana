package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ConsultantRoute(
    viewModel: ConsultantViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.stateFlow.collectAsState(ConsultantState())
    val actions = rememberConsultantActions(viewModel)
    ConsultantScreen(uiState, actions,navController)
}


@Composable
fun rememberConsultantActions(viewModel: ConsultantViewModel): ConsultantActions {
    return remember(viewModel) {
        ConsultantActions(
            onSubmit = viewModel::submit
        )
    }
}