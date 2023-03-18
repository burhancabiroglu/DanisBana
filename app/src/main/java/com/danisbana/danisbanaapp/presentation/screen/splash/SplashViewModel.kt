package com.danisbana.danisbanaapp.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import com.danisbana.danisbanaapp.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepo: FirebaseAuthRepo,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<SplashState> = MutableStateFlow(SplashState())
    val stateFlow: StateFlow<SplashState> = _stateFlow.asStateFlow()


    fun updateState(callBack: () -> Unit) {
        viewModelScope.launch {
            authRepo.initFCMTokenAsync().await()
        }
        viewModelScope.launch {
            _stateFlow.emit(SplashState(true))
            authRepo.getCurrentUser()?.let {
                val state = _stateFlow.value
                _stateFlow.tryEmit(state.copy(screen = Screen.Home))
            }
            authRepo.getAppUserAsync().await().let {
                val user = it.getOrNull()?:return@launch
                Screen.ConditionalScreen.route =
                    if(user.info?.userRole?.admin == true) Screen.AdminPanel.route
                    else Screen.Messages.route
            }
            callBack.invoke()
        }
    }
}