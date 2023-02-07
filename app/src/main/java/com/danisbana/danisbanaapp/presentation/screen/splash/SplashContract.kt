package com.danisbana.danisbanaapp.presentation.screen.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class SplashState(
    val startAnim:Boolean = false
)

data class SplashActions(
    val onClick: () -> Unit = {}
)