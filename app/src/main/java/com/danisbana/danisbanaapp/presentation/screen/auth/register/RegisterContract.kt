package com.danisbana.danisbanaapp.presentation.screen.auth.register

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.danisbana.danisbanaapp.core.model.register.RegisterRequest

class RegisterState {
    var fullName by mutableStateOf(TextFieldValue())
    var email by mutableStateOf(TextFieldValue())
    var password by mutableStateOf(TextFieldValue())
    var passwordConfirm by mutableStateOf(TextFieldValue())
    var isPolicyChecked by mutableStateOf(false)

    var pageLoading by mutableStateOf(false)

    var formState by mutableStateOf(RegistrationFormState())

    val buttonEnabled by derivedStateOf {
        email.text.isNotEmpty() &&
        fullName.text.isNotEmpty()
        password.text.isNotEmpty() &&
        passwordConfirm.text.isNotEmpty() &&
        isPolicyChecked
    }


    fun buildRegisterRequest(): RegisterRequest {
        return RegisterRequest(
            fullName = fullName.text.trim(),
            email = email.text.trim(),
            password = password.text.trim()
        )
    }
}

data class RegistrationFormState(
    val emailError: String? = null,
    val passwordError: String? = null,
    val repeatedPasswordError: String? = null,
    val termsError: String? = null
)

data class RegisterActions(
    val policyCheckAction: (Boolean) -> Unit = {},
    val routeLogin: () -> Unit = {},
    val tryRegister: () -> Unit = {}
)


sealed interface RegisterNavChannel {
    object RouteLogin: RegisterNavChannel
}