package com.danisbana.danisbanaapp.presentation.screen.auth.login

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.stateFlow.collectAsState()
    val actions = rememberLoginActions(viewModel)
    LoginScreen(uiState, actions)

    LaunchedEffect(true) {
        viewModel.navChannel.collectLatest {
            when(it){
                LoginNavChannel.RouteRegister -> navController.navigate(Screen.Register.route)
                LoginNavChannel.RouteHome -> {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
                LoginNavChannel.RouteForgotPassword -> {
                    navController.navigate(Screen.ForgotPassword.route)
                }
            }
        }
    }
}


@Composable
fun rememberLoginActions(viewModel: LoginViewModel): LoginActions {
    return remember(viewModel) {
        LoginActions(
            routeRegister = viewModel::routeRegister,
            tryLogin = viewModel::tryLogin,
            routeForgotPassword = viewModel::routeForgotPassword
        )
    }
}