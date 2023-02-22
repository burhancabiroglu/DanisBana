package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.domain.repo.FirebaseAuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepo: FirebaseAuthRepo
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
    val stateFlow: StateFlow<ProfileState> = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _stateFlow.value.pageLoading.show()
            val result = authRepo.getAppUserAsync().await()
            if(result.isSuccess) {
                _stateFlow.value.appUser = result.getOrNull()
            }
            _stateFlow.value.pageLoading.hide()
        }
    }

    fun logout() {
        authRepo.signOut()
    }
}