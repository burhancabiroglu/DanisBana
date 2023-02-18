package com.danisbana.danisbanaapp.presentation.screen.auth.login

import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.usecase.ValidateEmail
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo
) : ViewModel() {

    var snackBarState = SnackbarHostState()

    private val _stateFlow: MutableStateFlow<LoginState> = MutableStateFlow(LoginState(snackBarState))
    val stateFlow: StateFlow<LoginState> = _stateFlow.asStateFlow()

    private val _navChannel = Channel<LoginNavChannel>()
    val navChannel get() = _navChannel.receiveAsFlow()

    private val validateEmail = ValidateEmail()


    fun routeRegister() {
        viewModelScope.launch {
            _navChannel.send(LoginNavChannel.RouteRegister)
        }
    }

    fun tryLogin() {
        val state = _stateFlow.value
        val emailResult = validateEmail.execute(state.email.text)


        val hasError = listOf(emailResult).any { !it.successful }

        if(hasError) {
            state.formState = state.formState.copy(emailError = emailResult.errorMessage)
            return
        }
        viewModelScope.launch {
            _stateFlow.value.pageLoading = true
            val request = _stateFlow.value.buildLoginRequest()
            val result = firebaseAuthRepo.loginAsync(request).await()
            result.onFailure(::onSubmitFailure)
            result.onSuccess(::onSubmitSuccess)
            _stateFlow.value.pageLoading = false
        }
    }
    private fun onSubmitFailure(t: Throwable) {
        Log.e("TAG", "onSubmitFailure: $t ", )
        viewModelScope.launch {
            when(t){
                is FirebaseAuthInvalidCredentialsException ->
                    snackBarState.showSnackbar(
                        actionLabel = "error",
                        message = "E-posta veya parola hatalı"
                    )
                is FirebaseAuthInvalidUserException ->
                    snackBarState.showSnackbar(
                        actionLabel = "error",
                        message = "Böyle bir kullanıcı mevcut değil"
                    )
            }
        }
    }

    private fun onSubmitSuccess(r: AuthResult) {
        viewModelScope.launch {
            snackBarState.showSnackbar(
                actionLabel = "success",
                message = "Giriş Başarılı"
            )
            _navChannel.send(LoginNavChannel.RouteHome)
        }
    }
}