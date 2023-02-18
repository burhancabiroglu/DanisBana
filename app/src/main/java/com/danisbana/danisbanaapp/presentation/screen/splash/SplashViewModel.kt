package com.danisbana.danisbanaapp.presentation.screen.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepo: FirebaseAuthRepo
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<SplashState> = MutableStateFlow(SplashState())
    val stateFlow: StateFlow<SplashState> = _stateFlow.asStateFlow()


    fun updateState() {
        viewModelScope.launch {
            _stateFlow.emit(SplashState(true))
            authRepo.getCurrentUser()?.let {
                val state = _stateFlow.value
                _stateFlow.tryEmit(state.copy(screen = Screen.Home))
            }
        }
    }
}