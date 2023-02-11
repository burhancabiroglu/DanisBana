package com.danisbana.danisbanaapp.presentation.screen.auth.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.danisbana.danisbanaapp.presentation.screen.auth.login.LoginNavChannel

class RegisterState {
    var fullName by mutableStateOf(TextFieldValue())
    var email by mutableStateOf(TextFieldValue())
    var password by mutableStateOf(TextFieldValue())
    var passwordConfirm by mutableStateOf(TextFieldValue())
    var isPolicyChecked by mutableStateOf(false)
}

sealed interface RegisterNavChannel {
    object RouteLogin: RegisterNavChannel
}