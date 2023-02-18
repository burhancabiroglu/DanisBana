package com.danisbana.danisbanaapp.presentation.screen.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.core.model.profile.AppUser
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
            authRepo.getCurrentUser()?.let {
                _stateFlow.tryEmit(
                    ProfileState(
                        AppUser(it)
                    )
                )
            }
        }
    }

    fun logout() {
        authRepo.signOut()
    }
}