package com.danisbana.danisbanaapp.presentation.screen.auth.login

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.danisbana.danisbanaapp.core.model.login.LoginRequest
import com.danisbana.danisbanaapp.domain.base.LoadingState
import com.danisbana.danisbanaapp.presentation.components.indicator.PageLoading
import com.danisbana.danisbanaapp.presentation.screen.auth.register.RegistrationFormState

class LoginState(
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
) {

    var email by mutableStateOf(TextFieldValue())
    var password by mutableStateOf(TextFieldValue())

    var pageLoading = LoadingState(false)
    var formState by mutableStateOf(LoginFormState())


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

data class LoginFormState(
    val emailError: String? = null,
)

data class LoginActions(
    val onClick: () -> Unit = {},
    val routeRegister: () -> Unit = {},
    val tryLogin: () -> Unit = {}
)

sealed interface LoginNavChannel {
    object RouteRegister: LoginNavChannel
    object RouteHome: LoginNavChannel
}