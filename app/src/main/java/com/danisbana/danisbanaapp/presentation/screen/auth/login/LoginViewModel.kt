package com.danisbana.danisbanaapp.presentation.screen.auth.login

import androidx.compose.material.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.core.model.profile.AppUser
import com.danisbana.danisbanaapp.core.util.FirebaseEmailVerificationException
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.usecase.ValidateEmail
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
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

    fun routeForgotPassword() {
        viewModelScope.launch {
            _navChannel.send(LoginNavChannel.RouteForgotPassword)
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
            _stateFlow.value.pageLoading.show()
            val request = _stateFlow.value.buildLoginRequest()
            val result = firebaseAuthRepo.loginAsync(request).await()
            result.onFailure(::onSubmitFailure)
            result.onSuccess(::onSubmitSuccess)
            _stateFlow.value.pageLoading.hide()
        }
    }
    private fun onSubmitFailure(t: Throwable) {
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
                is FirebaseTooManyRequestsException ->
                    snackBarState.showSnackbar(
                        actionLabel = "error",
                        message = "Çok fazla hatalı erişim talebi aldık.\nHesabınızı koruma adına\nkısa süreli olarak durdurduk"
                    )
                is FirebaseEmailVerificationException ->
                    snackBarState.showSnackbar(
                        actionLabel = "error",
                        message = t.message
                    )
            }
        }
    }

    private fun onSubmitSuccess(user: AppUser) {
        viewModelScope.launch {
            Screen.ConditionalScreen.route =
                if(user.info?.userRole?.admin == true) Screen.AdminPanel.route
                else Screen.Messages.route
            snackBarState.showSnackbar(
                actionLabel = "success",
                message = "Giriş Başarılı"
            )
        }
        viewModelScope.launch {
            delay(400)
            _navChannel.send(LoginNavChannel.RouteHome)
        }
    }
}