package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents ProfileScreen
 **/
class ProfileState

/**
 * Profile Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ProfileActions(
    val onClick: () -> Unit = {}
)