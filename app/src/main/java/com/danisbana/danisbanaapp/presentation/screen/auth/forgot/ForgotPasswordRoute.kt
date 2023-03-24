package com.danisbana.danisbanaapp.presentation.screen.auth.forgot

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import com.danisbana.danisbanaapp.presentation.screen.auth.login.LoginNavChannel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ForgotPasswordRoute(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.stateFlow.collectAsState(ForgotPasswordState())
    val actions = rememberForgotPasswordActions(viewModel)
    actions.onBackAction = { navController.popBackStack() }
    ForgotPasswordScreen(uiState, actions)

    LaunchedEffect(true) {
        viewModel.navChannel.collectLatest {
            when(it){
                ForgotPasswordNavChannel.OnBack -> navController.popBackStack()
            }
        }
    }
}


@Composable
fun rememberForgotPasswordActions(viewModel: ForgotPasswordViewModel): ForgotPasswordActions {
    return remember(viewModel) {
        ForgotPasswordActions(
            submit = viewModel::trySubmit
        )
    }
}