package com.danisbana.danisbanaapp.presentation.screen.splash

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import kotlinx.coroutines.delay
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SplashRoute(viewModel: SplashViewModel = hiltViewModel(),navController: NavController) {
    val uiState by viewModel.stateFlow.collectAsState(SplashState())
    val actions = rememberSplashActions(viewModel)
    val uiController = rememberSystemUiController()

    SplashScreen(uiState, actions)

    LaunchedEffect(key1 = Unit) {
        viewModel.updateState()
        delay(2000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    DisposableEffect(Unit){
        uiController.setStatusBarColor(color = Color.Transparent)
        onDispose {  }
    }
}


@Composable
fun rememberSplashActions(viewModel: SplashViewModel): SplashActions {
    return remember(viewModel) {
        SplashActions()
    }
}