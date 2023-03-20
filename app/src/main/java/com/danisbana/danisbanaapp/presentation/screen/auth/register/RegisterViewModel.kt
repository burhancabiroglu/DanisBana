package com.danisbana.danisbanaapp.presentation.screen.auth.register

import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.repo.FirebaseConfigRepo
import com.danisbana.danisbanaapp.domain.usecase.ValidateEmail
import com.danisbana.danisbanaapp.domain.usecase.ValidatePassword
import com.danisbana.danisbanaapp.domain.usecase.ValidateRepeatedPassword
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo,
    private val firebaseConfigRepo: FirebaseConfigRepo
) : ViewModel() {

    var snackBarState = SnackbarHostState()
    private val _stateFlow: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState(snackBarState))
    val stateFlow: StateFlow<RegisterState> = _stateFlow.asStateFlow()

    private val _navChannel = Channel<RegisterNavChannel>()
    val navChannel get() = _navChannel.receiveAsFlow()

    private val validateEmail = ValidateEmail()
    private val validatePassword = ValidatePassword()
    private val validateRepeatedPassword = ValidateRepeatedPassword()


    init {
        viewModelScope.launch {
            val result = firebaseConfigRepo.getAgreementConfigAsync().await()
            _stateFlow.value.agreement = result.getOrNull()
        }
    }

    fun routeLogin() {
        viewModelScope.launch {
            _navChannel.send(RegisterNavChannel.RouteLogin)
        }
    }

    fun routeSuccess() {
        viewModelScope.launch {
            _navChannel.send(RegisterNavChannel.RouteSuccess)
        }
    }

    fun policyCheckAction(boolean: Boolean) {
        _stateFlow.value.isPolicyChecked = boolean
    }

    fun tryRegister() {
        val state = _stateFlow.value
        val emailResult = validateEmail.execute(state.email.text)
        val passwordResult = validatePassword.execute(state.password.text)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            state.password.text, state.passwordConfirm.text
        )

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
        ).any { !it.successful }

        if(hasError) {
            state.formState = state.formState.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            val request = _stateFlow.value.buildRegisterRequest()
            val result = firebaseAuthRepo.registerAsync(request).await()
            result.onFailure(::onSubmitFailure)
            result.onSuccess(::onSubmitSuccess)
            _stateFlow.value.pageLoading.hide()
        }
    }

    private fun onSubmitFailure(t: Throwable) {
        viewModelScope.launch {
            when(t){
                is FirebaseAuthUserCollisionException ->
                    snackBarState.showSnackbar(
                        actionLabel = "error",
                        message = "Bu e-posta adresi\nbaşka bir hesap tarafından\nkullanılıyor"
                    )
                is FirebaseAuthEmailException ->
                    snackBarState.showSnackbar(
                        actionLabel = "error",
                        message = "Bu e-posta adresi hatalıdır"
                    )
            }
        }
    }

    private fun onSubmitSuccess(r: AuthResult) {
        viewModelScope.launch {
            snackBarState.showSnackbar(
                actionLabel = "success",
                message = "Kayıt işlemi başarılı oldu.\nGiriş sayfasına yönlendiriliyorsunuz"
            )
        }
        viewModelScope.launch {
            delay(400)
            routeSuccess()
        }
    }
}