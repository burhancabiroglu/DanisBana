package com.danisbana.danisbanaapp.presentation.screen.auth.login

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.danisbana.danisbanaapp.core.model.login.LoginRequest

class LoginState {
    var email by mutableStateOf(TextFieldValue())
    var password by mutableStateOf(TextFieldValue())

    var pageLoading by mutableStateOf(false)

    fun buildLoginRequest(): LoginRequest {
        return LoginRequest(
            email = email.text.trim(),
            password = password.text.trim()
        )
    }

    val buttonEnabled by derivedStateOf {
        email.text.isNotEmpty() && password.text.isNotEmpty()
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