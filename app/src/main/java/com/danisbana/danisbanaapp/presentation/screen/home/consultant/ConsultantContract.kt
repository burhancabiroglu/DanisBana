package com.danisbana.danisbanaapp.presentation.screen.home.consultant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents ConsultantScreen
 **/
class ConsultantState

data class ConsultantActions(
    val onClick: () -> Unit = {}
)
