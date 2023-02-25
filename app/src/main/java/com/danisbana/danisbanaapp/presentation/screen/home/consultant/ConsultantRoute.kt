package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ConsultantRoute(
    viewModel: ConsultantViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.stateFlow.collectAsState(ConsultantState())
    val actions = rememberConsultantActions(viewModel)

    actions.onBack = {
        navController.popBackStack()
        navController.currentBackStackEntry?.updateState()
    }

    ConsultantScreen(uiState, actions)

    LaunchedEffect(true) {
        viewModel.navChannel.collectLatest {
            when(it){
                ConsultantNavChannel.OnBack -> actions.onBack()
            }
        }
    }
}


@Composable
fun rememberConsultantActions(viewModel: ConsultantViewModel): ConsultantActions {
    return remember(viewModel) {
        ConsultantActions(
            onSubmit = viewModel::submit
        )
    }
}