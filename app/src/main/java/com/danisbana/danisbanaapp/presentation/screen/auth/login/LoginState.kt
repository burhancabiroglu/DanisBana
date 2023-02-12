package com.danisbana.danisbanaapp.presentation.screen.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue

class LoginState {
    var email by mutableStateOf(TextFieldValue())
    var password by mutableStateOf(TextFieldValue())
}

sealed interface LoginNavChannel {
    object RouteRegister: LoginNavChannel
    object RouteHome: LoginNavChannel

}