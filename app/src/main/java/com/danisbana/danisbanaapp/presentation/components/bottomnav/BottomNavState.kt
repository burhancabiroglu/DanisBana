package com.danisbana.danisbanaapp.presentation.components.bottomnav

import androidx.compose.runtime.*


class BottomNavState(initial: Int = 0) {
    var selectedIndex by mutableStateOf(initial)
}


@Composable
fun rememberBottomNavState() =
    remember {
        BottomNavState()
    }
