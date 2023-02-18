package com.danisbana.danisbanaapp.presentation.screen.auth.register

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.repo.FirebaseConfigRepo
import com.danisbana.danisbanaapp.domain.usecase.ValidateEmail
import com.danisbana.danisbanaapp.domain.usecase.ValidatePassword
import com.danisbana.danisbanaapp.domain.usecase.ValidateRepeatedPassword
import com.danisbana.danisbanaapp.domain.usecase.ValidateTerms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo,
    private val firebaseConfigRepo: FirebaseConfigRepo
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())
    val stateFlow: StateFlow<RegisterState> = _stateFlow.asStateFlow()

    private val _navChannel = Channel<RegisterNavChannel>()
    val navChannel get() = _navChannel.receiveAsFlow()

    private val validateEmail = ValidateEmail()
    private val validatePassword = ValidatePassword()
    private val validateRepeatedPassword = ValidateRepeatedPassword()
    //private val validateTerms = ValidateTerms()


    init {
        viewModelScope.launch {
           // val result = firebaseConfigRepo.getAgreementConfigAsync().await()
        }
    }

    fun routeLogin() {
        viewModelScope.launch {
            _navChannel.send(RegisterNavChannel.RouteLogin)
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
        //val termsResult = validateTerms.execute(state.isPolicyChecked)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            //termsResult
        ).any { !it.successful }

        if(hasError) {
            state.formState = state.formState.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                //termsError = termsResult.errorMessage
            )
            return
        }


        viewModelScope.launch {
            _stateFlow.value.pageLoading = true
            val result = firebaseAuthRepo.registerAsync(
                _stateFlow.value.buildRegisterRequest()
            ).await()
            _stateFlow.value.pageLoading = false
            when {
                result.isSuccess -> {
                    Log.e("TAG", "isSuccess: $result", )
                }
                result.isFailure -> {
                    Log.e("TAG", "isFailure: $result", )
                }
            }
            //_navChannel.send(LoginNavChannel.RouteHome)
        }
    }
}