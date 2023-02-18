package com.danisbana.danisbanaapp.presentation.screen.auth.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.core.repo.FirebaseAuthRepoImpl
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val firebaseAuthRepo: FirebaseAuthRepo
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val stateFlow: StateFlow<LoginState> = _stateFlow.asStateFlow()

    private val _navChannel = Channel<LoginNavChannel>()
    val navChannel get() = _navChannel.consumeAsFlow()


    fun routeRegister() {
        viewModelScope.launch {
            _navChannel.send(LoginNavChannel.RouteRegister)
        }
    }

    fun tryLogin() {
        viewModelScope.launch {
            val request = _stateFlow.value.buildLoginRequest()
            val result = firebaseAuthRepo.loginAsync(request).await()
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