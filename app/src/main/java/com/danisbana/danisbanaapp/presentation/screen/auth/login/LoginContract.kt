package com.danisbana.danisbanaapp.presentation.screen.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.danisbana.danisbanaapp.core.model.login.LoginRequest

class LoginState {
    var email by mutableStateOf(TextFieldValue())
    var password by mutableStateOf(TextFieldValue())

    fun buildLoginRequest(): LoginRequest {
        return LoginRequest(
            email = email.text.trim(),
            password = password.text.trim()
        )
    }
}

data class LoginActions(
    val onClick: () -> Unit = {},
    val routeRegister: () -> Unit = {},
    val tryLogin: () -> Unit = {}
)

sealed interface LoginNavChannel {
    object RouteRegister: LoginNavChannel
    object RouteHome: LoginNavChannel
}