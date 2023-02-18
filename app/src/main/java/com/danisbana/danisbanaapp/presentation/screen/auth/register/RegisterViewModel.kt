package com.danisbana.danisbanaapp.presentation.screen.auth.register

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.domain.repo.FirebaseConfigRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val firebaseAuthRepo: FirebaseAuthRepo,
    private val firebaseConfigRepo: FirebaseConfigRepo
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())
    val stateFlow: StateFlow<RegisterState> = _stateFlow.asStateFlow()

    private val _navChannel = Channel<RegisterNavChannel>()
    val navChannel get() = _navChannel.consumeAsFlow()


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

    fun tryRegister() {
        viewModelScope.launch {
            val result = firebaseAuthRepo.registerAsync(
                _stateFlow.value.buildRegisterRequest()
            ).await()
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