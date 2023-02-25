package com.danisbana.danisbanaapp.presentation.screen.auth.register

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import kotlinx.coroutines.flow.collectLatest


@Composable
fun RegisterRoute(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.stateFlow.collectAsState()
    val actions = rememberRegisterActions(viewModel)

    RegisterScreen(uiState, actions)

    LaunchedEffect(true) {
        viewModel.navChannel.collectLatest {
            when(it){
                is RegisterNavChannel.RouteLogin -> navController.navigate(Screen.Login.route)
                is RegisterNavChannel.RouteSuccess -> navController.navigate(Screen.Success.route)
            }
        }
    }
}


@Composable
fun rememberRegisterActions(viewModel: RegisterViewModel): RegisterActions {
    return remember(viewModel) {
        RegisterActions(
            routeLogin = viewModel::routeLogin,
            tryRegister = viewModel::tryRegister,
            policyCheckAction = viewModel::policyCheckAction,
            routeSuccess = viewModel::routeSuccess
        )
    }
}