package com.danisbana.danisbanaapp.domain.base

import androidx.compose.runtime.*

@Stable
class LoadingState(
    initial: Boolean
) {
    var isLoading: Boolean by mutableStateOf(initial)

    fun show(){
        isLoading = true
    }
    fun hide(){
        isLoading = false
    }
}

@Composable
fun rememberLoadingState(loading: Boolean = false) = remember { LoadingState(loading) }