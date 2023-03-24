package com.danisbana.danisbanaapp.presentation.screen.auth.forgot

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.danisbana.danisbanaapp.domain.base.LoadingState
import com.danisbana.danisbanaapp.presentation.screen.auth.login.LoginFormState


class ForgotPasswordState(
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
) {
    var email by mutableStateOf(TextFieldValue())
    var pageLoading = LoadingState(false)
    var formState by mutableStateOf(LoginFormState())

    val buttonEnabled by derivedStateOf {
        email.text.isNotEmpty()
    }
}


data class ForgotPasswordActions(
    val submit: () -> Unit = {},
    var onBackAction: () -> Unit = {}
)


sealed interface ForgotPasswordNavChannel {
    object OnBack: ForgotPasswordNavChannel
}
