package com.danisbana.danisbanaapp.presentation.screen.splash

import com.danisbana.danisbanaapp.presentation.navigation.Screen

data class SplashState(
    val startAnim:Boolean = false,
    var screen: Screen = Screen.Login
)

data class SplashActions(
    val onClick: () -> Unit = {}
)