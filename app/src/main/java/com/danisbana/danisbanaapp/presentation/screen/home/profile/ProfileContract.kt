package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.compose.runtime.*


class ProfileState {
    var userName by mutableStateOf("Ali Cabbarov")
}

data class ProfileActions(
    val onClick: () -> Unit = {}
)