package com.danisbana.danisbanaapp.presentation.screen.splash

import android.app.Activity
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import com.danisbana.danisbanaapp.presentation.theme.Transparent
import com.danisbana.danisbanaapp.presentation.theme.White
import kotlinx.coroutines.delay
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SplashRoute(viewModel: SplashViewModel = hiltViewModel(),navController: NavController) {
    val uiState by viewModel.stateFlow.collectAsState(SplashState())
    val uiController = rememberSystemUiController()
    val localActivity = (LocalContext.current as Activity)

    LaunchedEffect(key1 = true) {
        viewModel.updateState()
        delay(1800)
        navController.popBackStack()
        navController.navigate(Screen.Login.route)
    }

    DisposableEffect(Unit){
        uiController.isNavigationBarVisible = false
        uiController.setStatusBarColor(Transparent)
        onDispose {
            WindowCompat.setDecorFitsSystemWindows(localActivity.window, false)
            uiController.isNavigationBarVisible = true
            uiController.setStatusBarColor(White)
        }
    }

    SplashScreen(uiState)
}


@Composable
fun rememberSplashActions(viewModel: SplashViewModel): SplashActions {
    return remember(viewModel) {
        SplashActions()
    }
}