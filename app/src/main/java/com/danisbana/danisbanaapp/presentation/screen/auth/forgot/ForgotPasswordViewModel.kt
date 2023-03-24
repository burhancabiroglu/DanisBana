package com.danisbana.danisbanaapp.presentation.screen.auth.forgot

import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.usecase.ValidateEmail
import com.danisbana.danisbanaapp.presentation.screen.auth.login.LoginNavChannel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    val authRepo: FirebaseAuthRepo
) : ViewModel() {
    var snackBarState = SnackbarHostState()

    private val _stateFlow: MutableStateFlow<ForgotPasswordState> = MutableStateFlow(ForgotPasswordState(snackBarState))
    val stateFlow: StateFlow<ForgotPasswordState> = _stateFlow.asStateFlow()

    private val _navChannel = Channel<ForgotPasswordNavChannel>()
    val navChannel get() = _navChannel.receiveAsFlow()

    private val validateEmail = ValidateEmail()

    fun trySubmit() {
        val state = _stateFlow.value
        val emailResult = validateEmail.execute(state.email.text)


        val hasError = listOf(emailResult).any { !it.successful }

        if(hasError) {
            state.formState = state.formState.copy(emailError = emailResult.errorMessage)
            return
        }
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            val result = authRepo.sendResetPasswordReqAsync(state.email.text).await()
            result.onFailure(::onSubmitFailure)
            result.onSuccess(::onSubmitSuccess)
            _stateFlow.value.pageLoading.hide()
        }
    }

    private fun onSubmitFailure(t: Throwable) {
        viewModelScope.launch {
            snackBarState.showSnackbar(
                actionLabel = "error",
                message = "E-posta veya parola hatalı"
            )
        }
    }

    private fun onSubmitSuccess(it:Any) {
        viewModelScope.launch {
            snackBarState.showSnackbar(
                actionLabel = "success",
                message = "Parola sıfırlama e-postası\ngönderildi"
            )
        }
        viewModelScope.launch {
            delay(800)
            _navChannel.send(ForgotPasswordNavChannel.OnBack)
        }
    }



}