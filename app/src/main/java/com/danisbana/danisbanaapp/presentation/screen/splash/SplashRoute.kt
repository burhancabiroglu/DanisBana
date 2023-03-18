package com.danisbana.danisbanaapp.presentation.screen.splash

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danisbana.danisbanaapp.presentation.theme.Transparent
import com.danisbana.danisbanaapp.presentation.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SplashRoute(viewModel: SplashViewModel = hiltViewModel(),navController: NavController) {
    val uiState by viewModel.stateFlow.collectAsState(SplashState())
    val uiController = rememberSystemUiController()

    LaunchedEffect(key1 = true) {
        viewModel.updateState {
            navController.popBackStack()
            navController.navigate(uiState.screen.route)
        }
    }

    DisposableEffect(Unit){
        uiController.isNavigationBarVisible = false
        uiController.setStatusBarColor(Transparent)
        onDispose {
            uiController.statusBarDarkContentEnabled = true
            uiController.isNavigationBarVisible = true
            uiController.setStatusBarColor(White)
        }
    }

    SplashScreen(uiState)
}